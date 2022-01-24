package com.airplane.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airplane.data.AllBookingResponse;
import com.airplane.data.BookRequestData;
import com.airplane.data.Booking;
import com.airplane.data.BookingResponse;
import com.airplane.data.ChangeBookingRequest;
import com.airplane.data.ErrorData;
import com.airplane.data.UserBookingResponse;
import com.airplane.mapper.BookingMapper;
import com.airplane.model.Flights;
import com.airplane.repository.BookingRepo;
import com.airplane.repository.FlightRepo;
import com.airplane.service.AirplaneService;
import com.airplane.service.GenerateAndSaveBooking;
import com.airplane.util.CompareByDate;

@Service
public class AirplaneServiceImpl implements AirplaneService {

	@Autowired
	GenerateAndSaveBooking generateAndSaveBooking;

	@Autowired
	FlightRepo flightRepo;

	@Autowired
	BookingMapper bookingMapper;

	@Autowired
	BookingRepo bookingRepo;

	private static final BigDecimal TAX = BigDecimal.valueOf(120.68);
	private static final BigDecimal BOOKINGCHARGE = BigDecimal.valueOf(150.25);
	private static final List<String> ALLOWEDSTATUS = Arrays.asList("CANCEL", "CONFIRM");

	@Override
	public BookingResponse bookFlight(BookRequestData bookRequestData) {

		BookingResponse bookingResponse = new BookingResponse(null, null);

		Flights flight = flightRepo.findByFlightIdAndPath(bookRequestData.getFlightPath(),
				bookRequestData.getFlightId());

		final String classType = bookRequestData.getTypeOfClass();

		if (!Objects.isNull(flight)) {
			int available = flight.getAvailable().getAvailableDetails(classType);
			BigDecimal price = BigDecimal.valueOf(flight.getPrice().getPriceDetails(classType));
			if (bookRequestData.getNumberOfTickets() > 5) {
				ErrorData errorData = new ErrorData(500, "Cannot Book more than 5 tickets at once");
				bookingResponse.setErrorData(errorData);
			} else {
				if (available >= bookRequestData.getNumberOfTickets()) {
					Booking bookingData = bookingMapper.convertRequestToBooking(bookRequestData);
					bookingData.setFlightName(flight.getFlightName());
					bookingData.setBookingStatus("INPROGRESS");
					BigDecimal ticketPrice = price.multiply(new BigDecimal(bookRequestData.getNumberOfTickets()));
					bookingData.setTicketPrice(ticketPrice.setScale(2, RoundingMode.HALF_DOWN).doubleValue());
					bookingData.setBookingCharge(BOOKINGCHARGE.setScale(2, RoundingMode.HALF_DOWN).doubleValue());
					BigDecimal tax = TAX.multiply(new BigDecimal(bookRequestData.getNumberOfTickets()));
					bookingData.setTax(tax.setScale(2, RoundingMode.HALF_DOWN).doubleValue());
					bookingData.setTotal(
							ticketPrice.add(BOOKINGCHARGE).add(tax).setScale(2, RoundingMode.HALF_DOWN).doubleValue());
					bookingData.setBookingId("BOOK" + generateAndSaveBooking.getSequence(Booking.SEQUENCE_NAME));
					bookingRepo.save(bookingData);
					flight.getAvailable().setAvailableDetails(classType,
							available - bookRequestData.getNumberOfTickets());
					flightRepo.save(flight);
					bookingResponse.setBooking(bookingData);
				} else {
					ErrorData errorData = new ErrorData(500, "Requested seats are not available currently");
					bookingResponse.setErrorData(errorData);
				}
			}
		} else {
			ErrorData errorData = new ErrorData(404, "Flights Not availble. Please validate your request");
			bookingResponse.setErrorData(errorData);
		}
		return bookingResponse;
	}

	@Override
	public BookingResponse changeStatus(ChangeBookingRequest changeBookingRequest) {
		BookingResponse bookingResponse = new BookingResponse(null, null);
		if (Boolean.TRUE.equals(checkStatus(changeBookingRequest.getStatus()))) {
			Optional<Booking> booking = bookingRepo.findByBookingId(changeBookingRequest.getBookingId());
			if (booking.isPresent() && Boolean.TRUE.equals(!checkSameStatus(changeBookingRequest, booking.get()))) {
				if (changeBookingRequest.getStatus().equalsIgnoreCase("cancel")
						&& (booking.get().getBookingStatus().equalsIgnoreCase("confirm")
								|| booking.get().getBookingStatus().equalsIgnoreCase("inprogress"))) {
					if (Boolean.TRUE.equals(isCancelEligible(booking.get().getBookingTime()))) {
						final String classType = booking.get().getTypeOfClass();
						Flights flight = flightRepo.findByFlightIdAndPath(booking.get().getFlightPath(),
								booking.get().getFlightId());
						flight.getAvailable().setAvailableDetails(classType,
								Math.addExact(flight.getAvailable().getAvailableDetails(classType),
										booking.get().getNumberOfTickets()));
						flightRepo.save(flight);
						booking.get().setBookingStatus(changeBookingRequest.getStatus());
						booking.get().setLastModified(LocalDateTime.now());
						bookingRepo.save(booking.get());
						bookingResponse.setBooking(booking.get());
					} else {
						ErrorData errorData = new ErrorData(500, "Cancel not permitted after 48 hours of journey date");
						bookingResponse.setErrorData(errorData);
					}
				} else if (changeBookingRequest.getStatus().equalsIgnoreCase("confirm")
						&& booking.get().getBookingStatus().equalsIgnoreCase("inprogress")) {
					booking.get().setBookingStatus(changeBookingRequest.getStatus());
					bookingRepo.save(booking.get());
					booking.get().setLastModified(LocalDateTime.now());
					bookingResponse.setBooking(booking.get());
				} else {
					ErrorData errorData = new ErrorData(500, "Not a Valid operation");
					bookingResponse.setErrorData(errorData);
				}

			} else {
				ErrorData errorData = new ErrorData(500, "Not a Valid operation");
				bookingResponse.setErrorData(errorData);
			}
		} else {
			ErrorData errorData = new ErrorData(500, "Booking not available.");
			bookingResponse.setErrorData(errorData);
		}
		return bookingResponse;
	}

	@Override
	public UserBookingResponse getUsersBookings(String userId) {
		UserBookingResponse userBookingResponse = new UserBookingResponse();
		List<Booking> bookings = bookingRepo.findBookingByUser(userId);
		Collections.sort(bookings, new CompareByDate());
		if (bookings.size() > 1) {
			Map<String, List<Booking>> bookingsMap = bookings.stream()
					.collect(Collectors.groupingBy(Booking::getBookingStatus));
			userBookingResponse.setUserBooking(bookingsMap);
		} else {
			ErrorData errorData = new ErrorData(500, "No Bookings available for the requested user");
			userBookingResponse.setErrorData(errorData);
		}
		return userBookingResponse;
	}

	@Override
	public AllBookingResponse allBookings() {
		AllBookingResponse allBookingResponse = new AllBookingResponse();
		List<Booking> bookings = bookingRepo.findAll();
		Collections.sort(bookings, new CompareByDate());
		if (bookings.size() > 1) {
			Map<String, Map<String, List<Booking>>> allBookings = bookings.stream().collect(
					Collectors.groupingBy(Booking::getBookingStatus, Collectors.groupingBy(Booking::getFlightName)));
			allBookingResponse.setAllBookings(allBookings);
		} else {
			ErrorData errorData = new ErrorData(500, "No Bookings available");
			allBookingResponse.setErrorData(errorData);
		}
		return allBookingResponse;

	}

	private final Boolean checkStatus(String status) {
		return ALLOWEDSTATUS.stream().anyMatch(status::equalsIgnoreCase);
	}

	private final Boolean checkSameStatus(ChangeBookingRequest changeBookingRequest, Booking booking) {
		return changeBookingRequest.getStatus().equalsIgnoreCase(booking.getBookingStatus());
	}

	private final Boolean isCancelEligible(LocalDateTime localDateTime) {
		return LocalDateTime.now().isBefore(localDateTime.minusDays(2));
	}
}

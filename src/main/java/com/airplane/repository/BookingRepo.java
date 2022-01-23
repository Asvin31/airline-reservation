package com.airplane.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.airplane.data.Booking;

public interface BookingRepo extends MongoRepository<Booking, String> {

	@Query("{bookingId : ?0}")
	Optional<Booking> findByBookingId(String bookingId);
	
	@Query("{userId : ?0}")
	List<Booking> findBookingByUser(String userId);
}

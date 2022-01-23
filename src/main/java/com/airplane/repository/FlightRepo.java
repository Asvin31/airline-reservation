package com.airplane.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.airplane.model.Flights;

public interface FlightRepo extends MongoRepository<Flights, String> {

	@Query("{flightPath :?0}")
	List<Flights> findByFlightPath(int flightPath);

	@Query("{flightPath :?0 , flightId: ?1}")
	Flights findByFlightIdAndPath(int flightPath, int flightId);
}

package com.airplane.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.airplane.model.FlightPaths;

public interface FlightPathRepo extends MongoRepository<FlightPaths, String> {
	List<FlightPaths> findAll();
}

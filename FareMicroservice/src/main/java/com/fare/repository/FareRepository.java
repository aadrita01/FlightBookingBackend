package com.fare.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fare.model.Fare;

public interface FareRepository extends MongoRepository<Fare,String> {
	
	public Fare findByFlightId(String flightId);
	


}

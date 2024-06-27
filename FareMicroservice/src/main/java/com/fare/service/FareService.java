package com.fare.service;

import java.math.BigDecimal;

import com.fare.exception.FlightIdNotFoundException;
import com.fare.model.Fare;

public interface FareService {
	
	public void defaultFare();
	
	public Fare getFareByFlightId(String FlightId);
	
	public Fare addFare(Fare fare);
	
	public Fare updateFare(String flightId, BigDecimal fare) throws FlightIdNotFoundException;
	
	public void deleteFare(String id) throws FlightIdNotFoundException;

}

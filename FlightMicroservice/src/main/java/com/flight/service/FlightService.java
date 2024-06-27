package com.flight.service;

import java.util.List;

import com.flight.exception.InvalidIdException;
import com.flight.model.Flights;


public interface FlightService {
	
	public Flights defaultFlight();
	
	public List<Flights> getAllFlights();
	
	public Flights getFlightById(String flightId) throws InvalidIdException;
	
	public Flights addFlight(Flights flight) throws InvalidIdException ;
	
	public Flights updateFlight(String flightNumber, Flights updatedFlight) throws InvalidIdException;
	
	public boolean deleteFlight(String flightNumber);

}

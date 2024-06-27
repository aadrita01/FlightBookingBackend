package com.flight.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.flight.exception.InvalidIdException;
import com.flight.model.Flights;
import com.flight.repository.FlightsRepository;

@Service
public class FlightServiceImpl implements FlightService {

	@Autowired
	FlightsRepository flightsRepository;

	public Flights defaultFlight() {
		Flights flight = new Flights();
		flight.setFlightId("ML4301");
		flight.setOrigin("Kolkata");
		flight.setDestination("Mumbai");
		flight.setDepartureDate(LocalDate.of(2024, 4, 12));
		flight.setDepartureTime(LocalTime.of(10, 45));
		flight.setArrivalDate(LocalDate.of(2024, 4, 12));
		flight.setArrivalTime(LocalTime.of(11, 45));
		flight.setSeatCapacity(100);
		return flightsRepository.save(flight);
	}

	public List<Flights> getAllFlights() {
		return flightsRepository.findAll();
	}

	public Flights getFlightById(String flightId) throws InvalidIdException {
		Optional<Flights> flight = null;
		if (flightsRepository.existsById(flightId)) {
			flight = flightsRepository.findById(flightId);
		} else {
			throw new InvalidIdException("Flight ID does not exits");
		}
		return flight.get();
	}

	public Flights addFlight(Flights flight) throws InvalidIdException {
		if (flightsRepository.existsById(flight.getFlightId())) {
			throw new InvalidIdException("Flight Id already exits");
		}
		return flightsRepository.save(flight);
	}

	public Flights updateFlight(String flightNumber, Flights updatedFlight) throws InvalidIdException {
		Optional<Flights> optionalFlight = flightsRepository.findById(flightNumber);
		if (optionalFlight.isPresent()) {
			Flights existingFlight = optionalFlight.get();
			existingFlight.setOrigin(updatedFlight.getOrigin());
			existingFlight.setDestination(updatedFlight.getDestination());
			existingFlight.setDepartureDate(updatedFlight.getDepartureDate());
			existingFlight.setDepartureTime(updatedFlight.getDepartureTime());
			existingFlight.setArrivalDate(updatedFlight.getArrivalDate());
			existingFlight.setArrivalTime(updatedFlight.getArrivalTime());
			existingFlight.setSeatCapacity(updatedFlight.getSeatCapacity());
			return flightsRepository.save(existingFlight);

		} else
			throw new InvalidIdException("Flight ID does not exits");
	}

	public boolean deleteFlight(String flightNumber) {
		if (flightsRepository.existsById(flightNumber)) {
			flightsRepository.deleteById(flightNumber);
			return true;
		}
		return false;
	}

}

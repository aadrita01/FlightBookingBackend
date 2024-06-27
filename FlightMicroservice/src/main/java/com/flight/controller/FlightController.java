package com.flight.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.flight.exception.InvalidIdException;
import com.flight.model.Flights;
import com.flight.service.FlightService;

@CrossOrigin("http://localhost:4200")
@RestController
public class FlightController {
	
	@Autowired
	public FlightService flightService;
	
	
	@GetMapping("/getflights")
	public List<Flights> getAllFlights(){
		
		flightService.defaultFlight();
		List<Flights> flights=flightService.getAllFlights();
		
		return flights;
	}
	
	@GetMapping("/getflights/{flightId}")
	public Flights getFlightById(@PathVariable String flightId) {
		try {
			return flightService.getFlightById(flightId);
		} catch (InvalidIdException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
		return null;
		
	}
	
	@PostMapping("/addflights") // Map ONLY POST Requests
	  public ResponseEntity<Flights> addFlight(@RequestBody Flights flight) {
		Flights addedFlight = null;
		try {
			addedFlight = flightService.addFlight(flight);
		} catch (InvalidIdException e) {
			// TODO Auto-generated catch block
			e.getMessage();
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(addedFlight, HttpStatus.CREATED);
	  }
	
	@PutMapping("updateflights/{flightNumber}")
	public ResponseEntity<Flights> updateFlight(@PathVariable String flightNumber,
			                                    @RequestBody Flights flight){
		Flights updatedFlight = null;
		try {
			updatedFlight = flightService.updateFlight(flightNumber, flight);
		} catch (InvalidIdException e) {
			// TODO Auto-generated catch block
			e.getMessage();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return updatedFlight!=null?new ResponseEntity<>(updatedFlight,HttpStatus.OK)
								  :new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("deleteflights/{flightNumber}")
	public ResponseEntity<Void> deleteFlight(@PathVariable String flightNumber){
		if(flightService.deleteFlight(flightNumber)) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}

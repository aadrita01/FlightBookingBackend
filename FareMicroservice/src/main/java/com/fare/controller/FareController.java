package com.fare.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fare.exception.FlightIdNotFoundException;
import com.fare.model.Fare;
import com.fare.service.FareService;

@CrossOrigin("http://localhost:4200")
@RestController
public class FareController {
	
	@Autowired
	private FareService fareService;
	
	@GetMapping("/getfare/{flightId}")
	public BigDecimal getFareByFlightId(@PathVariable String flightId){
		fareService.defaultFare();
		return fareService.getFareByFlightId(flightId).getAmount();
		
	}
	
	@PostMapping("/addfare")
	public ResponseEntity<Fare> addFare(@RequestBody Fare fare){
		return ResponseEntity.ok(fareService.addFare(fare));
	}
	
	@PutMapping("/updatefare/{id}")
	public ResponseEntity<Fare> updateFare(@PathVariable String id,@RequestBody Fare fare){
		System.out.println(fare.getAmount());
		
		Fare updatedFareResult = null;
		BigDecimal fare1=fare.getAmount();
		try {
			updatedFareResult = fareService.updateFare(id, fare1);
		} catch (FlightIdNotFoundException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
		return ResponseEntity.ok(updatedFareResult);
	}
	
	@DeleteMapping("/deletefare/{id}")
	public ResponseEntity<Void> deleteFare(@PathVariable String id){
		try {
			fareService.deleteFare(id);
		} catch (FlightIdNotFoundException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
		return ResponseEntity.noContent().build();
	}
}

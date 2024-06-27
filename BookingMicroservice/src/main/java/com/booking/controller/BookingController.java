package com.booking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.booking.model.ReferenceId;
import com.booking.service.BookingService;

@CrossOrigin("http://localhost:4200")
@RestController
public class BookingController {
	
	@Autowired
	private BookingService service;
	
	
	@PostMapping("/storeRefId/{refId}")
	public ResponseEntity<String> saveDetails(@PathVariable String refId) {
		ReferenceId ref=new ReferenceId(refId,false);
		service.storeRefId(ref);
		return ResponseEntity.ok("Data received successfully");
	}
	
	@GetMapping("/getRefId")
	public List<ReferenceId> getDetails() {
		return service.getRefId();
	}
	
	@PutMapping("/checkin/{refId}")
	public ResponseEntity<ReferenceId> updateDetails(@PathVariable String refId) {
		
		ReferenceId ref=service.updateRefId(refId);
		return ResponseEntity.ok(ref);
	}

}

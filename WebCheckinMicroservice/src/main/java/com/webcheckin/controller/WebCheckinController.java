package com.webcheckin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.webcheckin.model.Response;
import com.webcheckin.service.WebCheckinService;

@CrossOrigin("http://localhost:4200")
@RestController
public class WebCheckinController {
	
	@Autowired
	private WebCheckinService service;

	@GetMapping("/checkRefId/{refId}")
	public ResponseEntity<Response> checkIfExists(@PathVariable String refId) {
		System.out.println("webcheckin "+refId);
		Response resp=(service.checkIfExists(refId));
		return ResponseEntity.ok(resp);
	}
}

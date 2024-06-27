package com.search.controller;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import com.search.model.Flights;

@FeignClient(name="flight",url="localhost:8000")
public interface FlightProxy {
	
	@GetMapping("/getflights")
	public List<Flights> getAllFlights();

}

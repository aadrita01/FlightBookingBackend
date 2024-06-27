package com.search.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.search.model.Flights;
import com.search.service.SearchService;

@CrossOrigin("http://localhost:4200")
@RestController
public class SearchController {
	
	@Autowired
	private SearchService service;
	
	@GetMapping("/searchflights")
	public List<Flights> searchFlights(@RequestParam("origin") String origin,
										@RequestParam("destination") String destination,
										@RequestParam("departureDate") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate departureDate)
	{
		System.out.println("search");
		List<Flights> result=service.search(origin,destination,departureDate);
		return result;
		
	}

}

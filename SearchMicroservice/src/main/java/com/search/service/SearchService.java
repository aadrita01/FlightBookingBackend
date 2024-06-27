package com.search.service;

import java.time.LocalDate;
import java.util.List;

import com.search.model.Flights;

public interface SearchService {

	List<Flights> search(String origin, String destination, LocalDate departureDate);

//	List<Flights> search(String origin, String destination);
//
//	List<Flights> search();

}

package com.search.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.search.controller.FlightProxy;
import com.search.model.Flights;

@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	private FlightProxy proxy;

	@Override
	public List<Flights> search(String origin, String destination, LocalDate departureDate) {

		List<Flights> allFlights = proxy.getAllFlights();
		List<Flights> reqFlights = new ArrayList<Flights>();
		for (int i = 0; i < allFlights.size(); i++) {
			Flights flight = allFlights.get(i);
			if (flight.getOrigin().equalsIgnoreCase(origin)) {
				if (flight.getDestination().equalsIgnoreCase(destination)) {
					if (flight.getDepartureDate().isEqual(departureDate)) {
						reqFlights.add(flight);
					}
				}
			}
		}

		return reqFlights;
	}

//	@Override
//	public List<Flights> search(String origin, String destination) {
//		List<Flights> allFlights = proxy.getAllFlights();
//		List<Flights> reqFlights = new ArrayList<Flights>();
//		for (int i = 0; i < allFlights.size(); i++) {
//			Flights flight = allFlights.get(i);
//			if (flight.getOrigin().equalsIgnoreCase(origin)) {
//				if (flight.getDestination().equalsIgnoreCase(destination)) {
//
//					reqFlights.add(flight);
//
//				}
//			}
//			
//		}
//		return reqFlights;
//	}
//
//	@Override
//	public List<Flights> search() {
//		List<Flights> allFlights = proxy.getAllFlights();
//		return allFlights;
//	}
}

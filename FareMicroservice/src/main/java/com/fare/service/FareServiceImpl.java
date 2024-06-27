package com.fare.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fare.exception.FlightIdNotFoundException;
import com.fare.model.Fare;
import com.fare.repository.FareRepository;

@Service
public class FareServiceImpl implements FareService {

	@Autowired
	private FareRepository fareRepository;

	@Override
	public Fare getFareByFlightId(String FlightId) {
		return fareRepository.findByFlightId(FlightId);

	}

	@Override
	public void defaultFare() {
		Fare fare = new Fare("ML4301", new BigDecimal(2000));
		fareRepository.save(fare);
	}

	public Fare addFare(Fare fare) {
		return fareRepository.save(fare);
	}

	public Fare updateFare(String flightId, BigDecimal fare) throws FlightIdNotFoundException {
		Optional<Fare> existingFareOptional = fareRepository.findById(flightId);

		if (existingFareOptional.isPresent()) {
			Fare existingFare = existingFareOptional.get();
			existingFare.setAmount(fare);
			return fareRepository.save(existingFare);
		} else {
			throw new FlightIdNotFoundException("Invalid flight id");
		}
	}

	public void deleteFare(String id) throws FlightIdNotFoundException {
		if (fareRepository.existsById(id)) {
			fareRepository.deleteById(id);
		} else {
			throw new FlightIdNotFoundException("Invalid flight id");
		}
	}

}

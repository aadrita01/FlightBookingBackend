package com.booking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.model.ReferenceId;
import com.booking.repository.BookingRepository;

@Service
public class BookingServiceImpl implements BookingService{
	
	@Autowired
	private BookingRepository repo;

	@Override
	public ReferenceId storeRefId(ReferenceId refId) {
		return repo.save(refId);
		
	}

	@Override
	public List<ReferenceId> getRefId() {
		
		return repo.findAll();
	}

	@Override
	public ReferenceId updateRefId(String ref) {
		Optional<ReferenceId> existingRefOptional = repo.findById(ref);

		if (existingRefOptional.isPresent()) {
			ReferenceId existingRef = existingRefOptional.get();
			existingRef.setCheckdin(true);
			return repo.save(existingRef);
		}
		return null;
		// TODO Auto-generated method stub
		
	}

}

package com.booking.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.booking.model.ReferenceId;

public interface BookingRepository extends MongoRepository<ReferenceId,String>{

//	void save(String refId);

}

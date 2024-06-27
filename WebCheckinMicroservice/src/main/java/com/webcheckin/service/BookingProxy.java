package com.webcheckin.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.webcheckin.model.ReferenceId;

@FeignClient(name="booking",url="localhost:8004")
public interface BookingProxy {
	
	@GetMapping("/getRefId")
	public List<ReferenceId> getDetails();

}

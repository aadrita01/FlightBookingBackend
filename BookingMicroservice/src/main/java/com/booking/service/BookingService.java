package com.booking.service;

import java.util.List;

import com.booking.model.ReferenceId;

public interface BookingService {

//	void storeNop(int nop);

	ReferenceId storeRefId(ReferenceId refId);

	List<ReferenceId> getRefId();

	ReferenceId updateRefId(String refId);

}

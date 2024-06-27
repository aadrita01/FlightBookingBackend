package com.webcheckin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webcheckin.model.ReferenceId;
import com.webcheckin.model.Response;

@Service
public class WebCheckinServiceImpl implements WebCheckinService {

	@Autowired
	private BookingProxy proxy;

	@Autowired
	private Response response;

	@Override
	public Response checkIfExists(String refId) {
		List<ReferenceId> list = proxy.getDetails();
		String r=null;
		for(ReferenceId ref:list) {
			
			if(refId.equals(ref.getReferenceID()) && ref.isCheckdin()==false) {
				r="You have successfully checked in";
				response.setSuccess(true);
				break;
			}else if(refId.equals(ref.getReferenceID()) && ref.isCheckdin()==true){
				r="You are already checked in";
				break;
			}else {
				r="This Reference Id does not exist";
			}
		}
		
		response.setResponse(r);
		return response;
	}

}

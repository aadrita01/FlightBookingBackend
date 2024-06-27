package com.webcheckin.model;

import org.springframework.stereotype.Component;

@Component	
public class Response {

	private String response;
	private boolean success=false;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Response(String response) {
		super();
		this.response = response;
	}

	public Response() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	@Override
	public String toString() {
		return "Response [response=" + response + "]";
	}
	
	
}

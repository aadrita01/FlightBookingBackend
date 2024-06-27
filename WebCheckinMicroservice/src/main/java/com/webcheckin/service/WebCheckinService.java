package com.webcheckin.service;

import com.webcheckin.model.Response;

public interface WebCheckinService {

	Response checkIfExists(String refId);

}

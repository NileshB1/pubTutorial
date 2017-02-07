package com.pub.tutorial.rest;

import java.io.IOException;

import org.jboss.resteasy.client.ClientResponse;

import com.pub.tutorial.exception.BadResponseException;

public interface IRestRequest {
	ClientResponse<String> getRequest(String url);

	String getResponse(ClientResponse<String> response)
			throws BadResponseException, IOException;
}

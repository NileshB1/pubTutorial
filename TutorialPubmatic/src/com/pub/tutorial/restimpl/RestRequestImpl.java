package com.pub.tutorial.restimpl;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;

import com.pub.tutorial.constants.CommonConstants;
import com.pub.tutorial.exception.BadResponseException;
import com.pub.tutorial.rest.IRestRequest;

public class RestRequestImpl implements IRestRequest {

	@Override
	public ClientResponse<String> getRequest(String url) {
		ClientResponse<String> clientResponse = null;
		if (url != null && !url.isEmpty()) {
			ClientRequest clientRequest = new ClientRequest(url);
			clientRequest.accept(CommonConstants.CONTENT_TYPE_JSON);
			try {
				clientResponse = clientRequest.get(String.class);
			} catch (Exception e) {
				System.out
						.println("Exception occured while generating reponse: "
								+ e.getMessage());
			}
		}
		return clientResponse;
	}

	@Override
	public String getResponse(ClientResponse<String> response)
			throws BadResponseException, IOException {
		String output = null;
		StringBuilder jsonoutput = new StringBuilder();
		if (response == null
				|| response.getStatus() != CommonConstants.HTTP_SUCCESS) {
			throw new BadResponseException(
					"Respose is null or not returned successfully: " + response);
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(
				(new ByteArrayInputStream(response.getEntity().getBytes()))));
		while ((output = br.readLine()) != null) {
			jsonoutput.append(output.trim());
		}
		return jsonoutput.toString().trim();
	}

}

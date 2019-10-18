package com.cgi.smartcv.dto;

import java.io.IOException;

public class BoilerIO {

	// The BoilerIO handles specific request for the BoilerSimulator and returns the answer
	private BoilerConnector boilerConnector = new BoilerConnector();

	public String getCurrentStats() throws IOException {
		return boilerConnector.sendCommandToBoiler("$CV-STAT?");
	}
}

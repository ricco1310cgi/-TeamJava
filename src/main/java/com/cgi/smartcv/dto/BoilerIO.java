package com.cgi.smartcv.dto;

import java.io.IOException;

public class BoilerIO {

	// The BoilerIO handles specific request for the BoilerSimulator and returns the answer
	BoilerConnector boilerConnector = new BoilerConnector();

	String getCurrentStats() throws IOException {

		return boilerConnector.sendCommandToBoiler("$CV-STAT?");
	}
}

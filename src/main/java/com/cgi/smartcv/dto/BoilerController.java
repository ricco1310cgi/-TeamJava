package com.cgi.smartcv.dto;

import java.io.IOException;

public class BoilerController {
	private static Boiler boiler;
	private static BoilerIO boilerIO;
	private static BoilerConverter boilerConverter;
	private static BoilerConnector boilerConnector;

	// Constructor
	public BoilerController() {
		// Instantiate all Boiler objects
		boilerIO = new BoilerIO();
		boilerConverter = new BoilerConverter();
		boilerConnector = new BoilerConnector();
	}

	// Method to start the BoilerSimulator without establishing a connection, should
	// not be invoked directly
	public boolean startBoiler() throws IOException {
		return boilerConnector.runBoilerSimulator();
	}

	// Method to connect to the BoilerSimulator, implicitly starts the
	// BoilerSimulator if needed.
	public boolean connectBoiler() throws IOException, InterruptedException {
		return boilerConnector.connectBoiler();
	}

	// Method to stop the BoilerSimulator and close all streams
	public boolean destroyBoiler() throws IOException {
		return boilerConnector.stopBoilerSimulator();
	}

	// Method to take a command from the BoilerSimulator and create a Boiler object
	// based on the output
	public void outputBoiler() throws IOException {
		// Reads the String from the cv simulator on port 7777
		String boilerOutputString = boilerIO.getCurrentStats();

		// Create a BoilerDTO object from the String boilerOutputString
		boiler = boilerConverter.convertStringToBoilerDTO(boilerOutputString, boiler);
		// System.out.println(boiler.toString());
		System.out.println(boiler.getTempInside());
		System.out.println(boiler.getTempOutside());
	}
}

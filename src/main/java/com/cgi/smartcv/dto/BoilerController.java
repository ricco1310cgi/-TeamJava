package com.cgi.smartcv.dto;

import java.io.IOException;
import java.net.Socket;

public class BoilerController {
	private static Boiler boiler;
	private static BoilerIO boilerIO;
	private static BoilerConverter boilerConverter;
	private static BoilerConnector boilerConnector;
	private static Socket socket;

	public BoilerController() {
		// Instantiate all Boiler objects
		boilerIO = new BoilerIO();
		boilerConverter = new BoilerConverter();
		boilerConnector = new BoilerConnector();
		socket = new Socket();
	}

	public boolean connectBoiler() throws IOException{
	    socket = boilerConnector.connectBoiler();
	    return true;
    }

    public boolean destroyBoiler() throws IOException {
		return boilerConnector.stopBoilerSimulator();
	}


	public void outputBoiler() throws IOException {
		// Reads the String from the cv simulator on port 7777 !ASSIGN SECRET KEY IN CLASS!
        String boilerOutputString = boilerIO.getCurrentStats(socket);
        // Create a BoilerBTO object from the String boilerOutputString
        boiler = boilerConverter.convertStringToBoilerDTO(boilerOutputString, boiler);
        System.out.println(boiler.toString());
	}
}

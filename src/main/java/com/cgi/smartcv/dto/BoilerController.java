package com.cgi.smartcv.dto;

import java.io.IOException;

public class BoilerController {
	private static Boiler boiler;
	private static BoilerReader boilerReader;
	private static BoilerConverter boilerConverter;
	
	public static void outputBoiler() throws IOException {
		// Instantiate all Boiler objects
        boilerReader = new BoilerReader();
        boilerConverter = new BoilerConverter();

        // Reads the String from the cv simulator on port 7777 !ASSIGN SECRET KEY IN CLASS!
        String boilerOutputString = boilerReader.readFromBoiler(7777);
        // Create a BoilerBTO object from the String boilerOutputString
        boiler = boilerConverter.convertStringToBoilerDTO(boilerOutputString, boiler);
        System.out.println(boiler.toString());
	}
}

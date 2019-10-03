package com.cgi.smartcv.dto;

import com.cgi.smartcv.api.BoilerEndpoint;
import com.cgi.smartcv.persistence.BoilerService;

import javax.persistence.EntityManager;
import java.io.IOException;

public class BoilerController {
	private static Boiler boiler;
	private static BoilerReader boilerReader;
	private static BoilerConverter boilerConverter;

    private EntityManager entityManager;
	
	public static void outputBoiler() throws IOException {
		// Instantiate all Boiler objects
        boilerReader = new BoilerReader();
        boilerConverter = new BoilerConverter();

        // Reads the String from the cv simulator on port 7777 !ASSIGN SECRET KEY IN CLASS!
        String boilerOutputString = boilerReader.readFromBoiler();
        // Create a BoilerBTO object from the String boilerOutputString
        boiler = boilerConverter.convertStringToBoilerDTO(boilerOutputString, boiler);
        System.out.println(boiler.toString());
	}
}

package com.teamjava.SmartCV.api;

import java.util.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.teamjava.SmartCV.domain.BoilerDTO;
import com.teamjava.SmartCV.persistance.BoilerService;

@Path("smartcv")
public class CVEndpoint {
	
	private BoilerService boilerService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listAllTemps() {
		Iterable <BoilerDTO> boilers = boilerService.findAll();
		return Response.ok(boilers).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listOneTemp(long timeStamp) {
		Optional<BoilerDTO> boiler = boilerService.findById(timeStamp);
		return Response.ok(boiler).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response postTrein(BoilerDTO boiler) {
		BoilerDTO result = boilerService.save(boiler);
		return Response.accepted(result.getTimeRecorder()).build();
	}

}

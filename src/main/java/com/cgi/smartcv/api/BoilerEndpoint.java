package com.cgi.smartcv.api;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cgi.smartcv.dto.Boiler;
import com.cgi.smartcv.persistence.BoilerService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api")
public class BoilerEndpoint {

	private BoilerService boilerService;

	@Autowired
	public BoilerEndpoint(BoilerService boilerService) {
		this.boilerService = boilerService;
	}

	@ApiOperation(value = "View all boiler data from the database")
	@GetMapping("/boiler")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list of Boilers"),
			@ApiResponse(code = 404, message = "The boiler list is not found") })
	public ResponseEntity<Iterable<Boiler>> findAll() throws IOException {
		Iterable<Boiler> boilers = boilerService.findAll();
		if (boilers != null) {
			return ResponseEntity.ok(boilers);
		}
		return ResponseEntity.badRequest().build();
	}

	@ApiOperation(value = "Show temperature from the latest temperature from the database")
	@GetMapping("/boiler/temperature")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved temperature"),
			@ApiResponse(code = 404, message = "The temperature is not found") })
	public ResponseEntity<Float> findTemperature() {
		float tempInside = boilerService.findTemperature();
		return ResponseEntity.ok(tempInside);
	}

	@GetMapping("api/boiler/start")
	public ResponseEntity<Boolean> startBoiler() throws IOException, InterruptedException {
		return ResponseEntity.ok(boilerService.startBoiler());
	}
	
	@ApiOperation(value = "Save a boiler object to the database")
	@PostMapping("/boiler")
	@ApiResponses({ @ApiResponse(code = 200, message = "Successfully added a boiler object") })
	public ResponseEntity<Boiler> saveBoiler(@RequestBody @Valid Boiler boiler) {

		if (boiler != null) {
			return ResponseEntity.ok(boilerService.save(boiler));
		}
		return ResponseEntity.badRequest().build();
	}
}

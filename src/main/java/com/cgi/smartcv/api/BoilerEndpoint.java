package com.cgi.smartcv.api;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cgi.smartcv.dto.Boiler;
import com.cgi.smartcv.persistence.BoilerService;

@RestController
public class BoilerEndpoint {

	private BoilerService boilerService;

	@Autowired
	public BoilerEndpoint(BoilerService boilerService) {
		this.boilerService = boilerService;
	}

	@GetMapping("api/boiler")
	public ResponseEntity<Iterable<Boiler>> findAll() throws IOException {
		Iterable<Boiler> boilers = boilerService.findAll();
		return ResponseEntity.ok(boilers);
	}

	@GetMapping("api/boiler/temperature")
	public ResponseEntity<Float> findTemperature() {
		float tempInside = boilerService.findTemperature();
		return ResponseEntity.ok(tempInside);
	}
	
	@PostMapping("api/boiler")
	public ResponseEntity<Boiler> saveBoiler(@RequestBody @Valid Boiler boiler){
		if (boiler != null) {
			return ResponseEntity.ok(boilerService.save(boiler));
		}
		return ResponseEntity.badRequest().build();
	}
}

package com.cgi.smartcv.api;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
	
	@PostMapping("api/boiler")
	public ResponseEntity<Boiler> saveBoiler(@RequestBody @Valid Boiler boiler){
		if (boiler != null) {
			return ResponseEntity.ok(boilerService.save(boiler));
		}
		return ResponseEntity.badRequest().build();
	}

	@PostMapping("api/add")
	public @ResponseBody String addNewBoilerData(@RequestParam int boilerPressure, @RequestParam float tempInside, @RequestParam float tempOutside, @RequestParam boolean isDoorClosed, @RequestParam long timeMovementRecord, @RequestParam float gasUsage, @RequestParam long timeRecorder){
		Boiler b = new Boiler();
		b.setTempInside(tempInside);
		b.setBoilerPressure(boilerPressure);
		b.setDoorClosed(isDoorClosed);
		b.setGasUsage(gasUsage);
		b.setTempOutside(tempOutside);
		b.setTimeMovementRecord(timeMovementRecord);
		b.setTimeRecorder(timeRecorder);
		saveBoiler(b);
		return "Success";
	}
}

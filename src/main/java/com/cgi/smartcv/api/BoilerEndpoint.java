package com.cgi.smartcv.api;

import java.io.IOException;

import javax.validation.Valid;

import com.cgi.smartcv.persistence.BoilerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.cgi.smartcv.dto.Boiler;
import com.cgi.smartcv.persistence.BoilerService;

@Controller
@RequestMapping(path = "/api")
public class BoilerEndpoint {

	private BoilerService boilerService;
	private BoilerRepository boilerRepository;

	@Autowired
	public BoilerEndpoint(BoilerService boilerService) {
		this.boilerService = boilerService;
	}

	@GetMapping("/boiler")
	public ResponseEntity<Iterable<Boiler>> findAll() throws IOException {
		Iterable<Boiler> boilers = boilerService.findAll();
		return ResponseEntity.ok(boilers);
	}
	
	@PostMapping("/boiler")
	public ResponseEntity<Boiler> saveBoiler(@RequestBody @Valid Boiler boiler){
		if (boiler != null) {
			return ResponseEntity.ok(boilerService.saveData(boiler));
		}
		return ResponseEntity.badRequest().build();
	}

	@PostMapping("/add")
	@ResponseBody
	public ResponseEntity<Boiler> addNewBoilerData(){

		Boiler boiler1 = new Boiler();
		boiler1 = boilerService.convertString("#STAT#161#15.62#10.20#1#1488813845#0.03#1488814881", boiler1);
		return ResponseEntity.ok(boilerService.saveData(boiler1));

	}

}

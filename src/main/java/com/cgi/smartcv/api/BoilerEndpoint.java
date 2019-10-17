package com.cgi.smartcv.api;

import java.io.IOException;
import java.util.ArrayList;

import javax.validation.Valid;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cgi.smartcv.dto.Boiler;
import com.cgi.smartcv.persistence.BoilerService;

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
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully retrieved list of Boilers"),
            @ApiResponse(code = 404, message = "The boiler list is not found")})
    public ResponseEntity<Iterable<Boiler>> findAll() throws IOException, InterruptedException {
        Iterable<Boiler> boilers = boilerService.findAll();
        if (boilers != null) {
            return ResponseEntity.ok(boilers);
        }
        return ResponseEntity.badRequest().build();
    }

    @ApiOperation(value = "Show temperature from the latest temperature from the database")
    @GetMapping("/boiler/temperature")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully retrieved temperature"),
            @ApiResponse(code = 404, message = "The temperature is not found")})
    public ResponseEntity<Float> findTemperature() {
        float tempInside = boilerService.findTemperature();
        return ResponseEntity.ok(tempInside);
    }


    @ApiOperation(value = "Show average from the database")
    @GetMapping("/boiler/average")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully retrieved average"),
            @ApiResponse(code = 404, message = "Average is not found")})
    public ResponseEntity<ArrayList<Float>> getAverage() {
        ArrayList<Float> averages = boilerService.calculateAverage();
        return ResponseEntity.ok(averages);
    }

    @ApiOperation(value = "To start the Boiler")
    @GetMapping("/boiler/start")
    public ResponseEntity<Boolean> startBoiler() throws IOException, InterruptedException {
        return ResponseEntity.ok(boilerService.startBoiler());
    }

    @ApiOperation(value = "Save a boiler object to the database")
    @PostMapping("/boiler")
    @ApiResponses({@ApiResponse(code = 200, message = "Successfully added a boiler object")})
    public ResponseEntity<Boiler> saveBoiler(@RequestBody @Valid Boiler boiler) {
        if (boiler != null) {
            return ResponseEntity.ok(boilerService.saveData(boiler));
        }
        return ResponseEntity.badRequest().build();
    }

    @ApiOperation(value = "Add a new boiler object to the database")
    @PostMapping("/boiler/add")
    @ApiResponses({@ApiResponse(code = 200, message = "Successfully created a new boiler object")})
    public ResponseEntity<Boiler> addNewBoilerData() throws IOException {
        Boiler boiler = new Boiler();
        boiler = boilerService.getCurrentBoiler(boiler);
        // boiler1 =
        // boilerService.convertString("#STAT#161#15.62#10.20#1#1488813845#0.03#1488814881",
        // boiler1);
        return ResponseEntity.ok(boilerService.saveData(boiler));

    }

    @ApiOperation(value = "Control the boiler")
    @PostMapping("/boiler/temperature/{temperatureId}")
    public ResponseEntity<Boiler> setTemperature(@ApiParam(required = true, name = "temperatureId", value = "Temperature ID") @PathVariable("temperatureId") double temperatureId) {
        if (temperatureId > 23.5){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
        boolean boiler = boilerService.setTemperature(temperatureId);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/boilerDesc")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully retrieved list of Boilers in descending"),
            @ApiResponse(code = 404, message = "The boiler list is not found")})
    public ResponseEntity<Iterable<Boiler>> findAllByIdDesc() {
        Iterable<Boiler> boilers = boilerService.findAllByOrderByIdDesc();
        if (boilers != null) {
            return ResponseEntity.ok(boilers);
        }
        return ResponseEntity.badRequest().build();
    }

}

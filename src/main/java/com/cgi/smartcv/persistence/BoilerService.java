package com.cgi.smartcv.persistence;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cgi.smartcv.dto.Boiler;
import com.cgi.smartcv.dto.BoilerController;

@Service
@Transactional
public class BoilerService {

	private BoilerRepository boilerRepository;
	private BoilerController boilerController;

	@Autowired
	public BoilerService(BoilerRepository boilerRepository) {
		this.boilerRepository = boilerRepository;
	}

	public Iterable<Boiler> findAll() throws IOException, InterruptedException {
		Iterable<Boiler> result = boilerRepository.findAll();
		boilerController.outputBoiler();
		return result;
	}

	public Boiler save(Boiler boiler) {
		return boilerRepository.save(boiler);
	}

	public boolean startBoiler() throws IOException, InterruptedException {
		boilerController = new BoilerController();
		return boilerController.connectBoiler();
	}

	public float findTemperature() {
		Iterable<Boiler> boilers = boilerRepository.findAll();
		float tempInside = 0;
		long timeRecorder = 0;
		// find last (highest timeRecorder) boiler temperature in database
		for (Boiler b : boilers) {
			if (b.getTimeRecorder() > timeRecorder) {
				timeRecorder = b.getTimeRecorder();
				tempInside = b.getTempInside();
			}
		}
		return tempInside;
	}
}

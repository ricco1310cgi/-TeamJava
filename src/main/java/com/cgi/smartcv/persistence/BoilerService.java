package com.cgi.smartcv.persistence;

import java.io.IOException;

import com.cgi.smartcv.dto.BoilerConverter;
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
	private BoilerConverter boilerConverter;

	@Autowired
	public BoilerService(BoilerRepository boilerRepository) {
		this.boilerRepository = boilerRepository;
	}

	public Iterable<Boiler> findAll() throws IOException {
		Boiler boiler = new Boiler();
		boilerController = new BoilerController();
		Iterable<Boiler> result = boilerRepository.findAll();
		boilerController.outputBoiler();
		return result;
	}

	public Boiler saveData(Boiler boiler) {
		Boiler save = boilerRepository.save(boiler);
		return save;
	}

	public Boiler convertString(String input, Boiler inputBoiler){
		boilerConverter = new BoilerConverter();
		return boilerConverter.convertStringToBoilerDTO(input, inputBoiler);
	}
}

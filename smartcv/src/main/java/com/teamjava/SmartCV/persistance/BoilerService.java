package com.teamjava.SmartCV.persistance;

import java.util.Optional;

import com.teamjava.SmartCV.domain.BoilerDTO;

public class BoilerService {
	private BoilerRepository boilerRepository;

	public BoilerDTO save(BoilerDTO boiler){
		return boilerRepository.save(boiler);
	}
	
	public Optional<BoilerDTO> findById(Long timeStamp) {
		return boilerRepository.findById(timeStamp);
	}

	public Iterable <BoilerDTO> findAll(){
		Iterable <BoilerDTO> result = boilerRepository.findAll();
		return result;
	}
}

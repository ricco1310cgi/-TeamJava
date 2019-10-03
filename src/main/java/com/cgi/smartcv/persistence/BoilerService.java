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

}

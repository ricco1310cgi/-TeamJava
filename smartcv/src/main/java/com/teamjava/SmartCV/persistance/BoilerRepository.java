package com.teamjava.SmartCV.persistance;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.teamjava.SmartCV.domain.BoilerDTO;

@Component
public interface BoilerRepository extends CrudRepository<BoilerDTO, Long> {
	
}

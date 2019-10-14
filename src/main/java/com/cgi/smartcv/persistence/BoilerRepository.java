package com.cgi.smartcv.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.cgi.smartcv.dto.Boiler;

@Component
public interface BoilerRepository extends CrudRepository<Boiler, Long> {


}

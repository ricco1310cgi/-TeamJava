package com.cgi.smartcv;

import com.cgi.smartcv.dto.BoilerController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class SmartCvApplication {

	private static BoilerController boilerController;

	public static void main(String[] args) throws IOException, InterruptedException {
		SpringApplication.run(SmartCvApplication.class, args);

	}

}

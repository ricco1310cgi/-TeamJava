package com.cgi.smartcv.api;

import com.cgi.smartcv.security.SecurityHandshake;
import com.cgi.smartcv.security.SecurityRequest;
import com.cgi.smartcv.security.service.SecurityService;

import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.Valid;
import javax.xml.ws.http.HTTPException;

@RestController
@RequestMapping("/api/auth")
public class SecurityEndpoint {

	private SecurityService securityService;
	private SecurityHandshake handshake;

	@Autowired
	public SecurityEndpoint(SecurityService securityService) {
		this.securityService = securityService;
	}

	@ApiOperation(value = "Custom login using back-end value check and front-end handshake")
	@PostMapping("/signin")
	public ResponseEntity<SecurityHandshake> loginRico(@RequestBody SecurityRequest request) {
		System.out.println("Login attempt");
		try {
			handshake = securityService.login((request));
		} catch (HTTPException e) {
			if (e.getStatusCode() == 401) {
				return ResponseEntity.status(401).build();
			} else if (e.getStatusCode() == 403) {
				return ResponseEntity.status(403).build();
			}

		}
		return ResponseEntity.ok(handshake);
	}


}

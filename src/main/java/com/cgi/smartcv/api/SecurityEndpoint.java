package com.cgi.smartcv.api;

import com.cgi.smartcv.security.LogoutRequest;
import com.cgi.smartcv.security.SecurityHandshake;
import com.cgi.smartcv.security.SecurityRequest;
import com.cgi.smartcv.security.dto.Tokens;
import com.cgi.smartcv.security.dto.Users;
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
	public ResponseEntity<SecurityHandshake> login(@RequestBody SecurityRequest request) {
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

	@ApiOperation(value = "Delete login entry from database: Logout")
    @PostMapping("/signout")
    public ResponseEntity<Tokens> logout(@RequestBody LogoutRequest logoutRequest) {
		Tokens result = null;
		try {
			result = securityService.logout(logoutRequest);
		} catch (HTTPException e) {
			if (e.getStatusCode() == 404) {
				return ResponseEntity.status(404).build();
			}
		}
	    return ResponseEntity.ok(result);
    }

    @ApiOperation(value = "Add new users to the database")
    @PostMapping("/adduser")
	public ResponseEntity<Users> addUser(@RequestBody Users newUser) {
		Users addedUser = null;
		try {
			addedUser = securityService.addNewUser(newUser);
		} catch (HTTPException e) {
			if (e.getStatusCode() == 403)
			return ResponseEntity.status(403).build();
		}
		return ResponseEntity.ok(addedUser);
	}

	@ApiOperation(value = "Delete users from the database")
	@PostMapping("/deleteuser")
	public ResponseEntity<Users> deleteUser(@RequestBody Users userToDelete) {
		Users deletedUser = null;
		try {
			deletedUser = securityService.deleteUser(userToDelete);
		} catch (HTTPException e) {
			if (e.getStatusCode() == 404) {
				return ResponseEntity.status(404).build();
			}
		}
		return ResponseEntity.ok(deletedUser);
	}

	@ApiOperation(value = "Check if a user is logged in")
    @PostMapping("/checkloginstate")
    public ResponseEntity<Tokens> checkLoginState(@RequestBody Tokens tokenToCheck) {
	    Tokens responseToken = null;
	    try{
	        responseToken = securityService.checkActiveUser(tokenToCheck);
        } catch (HTTPException e) {
	        return ResponseEntity.status(404).build();
        }
	    return ResponseEntity.ok(responseToken);
    }
}

package com.openclassrooms.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.api.dto.UserDTO;
import com.openclassrooms.api.services.IUserService;

@RestController
public class AuthenticationController {
	
	@Autowired
	private IUserService iUserService;
	
	@PostMapping("/api/auth/register")
	public ResponseEntity<String> register(@RequestBody UserDTO userDTO) {
		return ResponseEntity.ok(iUserService.createUser(userDTO));
	}
}

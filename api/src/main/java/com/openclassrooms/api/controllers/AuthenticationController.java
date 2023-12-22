package com.openclassrooms.api.controllers;

import com.openclassrooms.api.exception.AuthenticationException;
import com.openclassrooms.api.response.UserResponse;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.openclassrooms.api.dto.UserDTO;
import com.openclassrooms.api.exception.UserException;
import com.openclassrooms.api.response.TokenResponse;
import com.openclassrooms.api.services.UserService;

import java.security.Principal;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<TokenResponse> register(@RequestBody UserDTO userDTO) {
		if(ObjectUtils.isEmpty(userDTO.getName()) || ObjectUtils.isEmpty(userDTO.getPassword()) || 
				ObjectUtils.isEmpty(userDTO.getEmail())) {
			throw new UserException("Tous les champs sont obligatoires");
		}
		return ResponseEntity.ok(new TokenResponse(userService.createUser(userDTO)));
	}
	@PostMapping("/login")
	public ResponseEntity<TokenResponse> login(@RequestBody UserDTO userDTO) {
		if(ObjectUtils.isEmpty(userDTO.getPassword()) || ObjectUtils.isEmpty(userDTO.getEmail())) {
			throw new AuthenticationException("error");
		}
		return ResponseEntity.ok(new TokenResponse(userService.login(userDTO)));
	}

	@GetMapping("/me")
	public ResponseEntity<UserResponse> me(Principal principalUser){
		return ResponseEntity.ok(userService.me(principalUser));
	}
}

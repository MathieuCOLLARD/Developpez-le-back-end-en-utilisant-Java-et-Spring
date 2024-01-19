package com.openclassrooms.api.controllers;

import com.openclassrooms.api.exception.Error;
import com.openclassrooms.api.response.UserResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import com.openclassrooms.api.dto.UserDTO;
import com.openclassrooms.api.response.TokenResponse;
import com.openclassrooms.api.services.UserService;

import java.security.Principal;
import java.util.HashMap;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AuthenticationController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/auth/register")
	public ResponseEntity<Object> register(@Valid @RequestBody UserDTO userDTO, Errors errors) {
		if(errors.hasErrors()) {
			return new ResponseEntity<>(new HashMap<>(), HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(new TokenResponse(userService.createUser(userDTO)));
	}
	@PostMapping("/auth/login")
	public ResponseEntity<Object> login(@RequestBody UserDTO userDTO) {
		Optional<String> token = userService.login(userDTO);
		if(token.isEmpty()) {
			return new ResponseEntity<>(new Error("error"), HttpStatus.UNAUTHORIZED);
		}
		return ResponseEntity.ok(new TokenResponse(token.get()));
	}

	@GetMapping("/auth/me")
	public ResponseEntity<UserResponse> me(Principal principalUser){
		return ResponseEntity.ok(userService.me(principalUser));
	}

	@GetMapping("/user/{id}")
	public ResponseEntity<UserResponse> getUser(@PathVariable Long id){
		return ResponseEntity.ok(userService.getUser(id));
	}
}

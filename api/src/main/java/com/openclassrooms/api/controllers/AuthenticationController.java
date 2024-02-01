package com.openclassrooms.api.controllers;

import com.openclassrooms.api.exception.Error;
import com.openclassrooms.api.response.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

	@Operation(summary = "Register a new user", description = "Return a token")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "User created",
					content = { @Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "400", description = "Invalid input",
					content = @Content)})
	@PostMapping("/auth/register")
	public ResponseEntity<Object> register(@Valid @RequestBody UserDTO userDTO, Errors errors) {
		if(errors.hasErrors()) {
			return new ResponseEntity<>(new HashMap<>(), HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(new TokenResponse(userService.createUser(userDTO)));
	}

	@Operation(summary = "Login a user", description = "Return a token")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "User logged in",
					content = { @Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "401", description = "Invalid credentials",
					content = @Content)})
	@PostMapping("/auth/login")
	public ResponseEntity<Object> login(@RequestBody UserDTO userDTO) {
		Optional<String> token = userService.login(userDTO);
		if(token.isEmpty()) {
			return new ResponseEntity<>(new Error("error"), HttpStatus.UNAUTHORIZED);
		}
		return ResponseEntity.ok(new TokenResponse(token.get()));
	}

	@Operation(summary = "Get the current user", description = "Return the current user")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "User logged in",
					content = { @Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "401", description = "Invalid credentials",
					content = @Content)})
	@GetMapping("/auth/me")
	public ResponseEntity<UserResponse> me(Principal principalUser){
		return ResponseEntity.ok(userService.me(principalUser));
	}

	@Operation(summary = "Get a user by id", description = "Return a user by id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "User found",
					content = { @Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "401", description = "Invalid credentials",
					content = @Content)})
	@GetMapping("/user/{id}")
	public ResponseEntity<UserResponse> getUser(@PathVariable Long id){
		return ResponseEntity.ok(userService.getUser(id));
	}
}

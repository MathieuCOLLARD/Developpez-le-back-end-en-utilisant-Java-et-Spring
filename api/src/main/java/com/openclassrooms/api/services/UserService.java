package com.openclassrooms.api.services;

import com.openclassrooms.api.dto.UserDTO;
import com.openclassrooms.api.response.UserResponse;

import java.security.Principal;
import java.util.Optional;

public interface UserService {

	/**
	 * @param userDTO
	 * @return String
	 */
	String createUser(UserDTO userDTO);

	/**
	 * @param userDTO
	 * @return Optional<String>
	 */
	Optional<String> login(UserDTO userDTO);

	/**
	 * @param user
	 * @return UserResponse
	 */
	UserResponse me(Principal user);

	/**
	 * @param id
	 * @return UserResponse
	 */
	UserResponse getUser(Long id);
}

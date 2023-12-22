package com.openclassrooms.api.services;

import com.openclassrooms.api.dto.UserDTO;
import com.openclassrooms.api.response.UserResponse;

import java.security.Principal;

public interface UserService {
	
	String createUser(UserDTO userDTO);

	String login(UserDTO userDTO);

	/**
	 * @param user
	 * @return
	 */
	UserResponse me(Principal user);
}

package com.openclassrooms.api.services;

import com.openclassrooms.api.dto.UserDTO;

public interface IUserService {
	
	String createUser(UserDTO userDTO);
}

package com.openclassrooms.api.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.api.dto.UserDTO;
import com.openclassrooms.api.entity.User;
import com.openclassrooms.api.repository.UserRepository;

import lombok.Data;

@Data
@Service
public class UserService implements IUserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    private ModelMapper modelMapper;
	
	@Autowired
	private JWTService jwtService;
	
	@Override
	public String createUser(UserDTO userDTO) {
		User user = modelMapper.map(userDTO, User.class);
		userRepository.save(user);
		return jwtService.generateToken(userDTO);
	}
	
	
}

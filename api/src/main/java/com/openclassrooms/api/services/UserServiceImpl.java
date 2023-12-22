package com.openclassrooms.api.services;

import com.openclassrooms.api.exception.AuthenticationException;
import com.openclassrooms.api.response.UserResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.openclassrooms.api.dto.UserDTO;
import com.openclassrooms.api.entity.UserEntity;
import com.openclassrooms.api.repository.UserRepository;

import lombok.Data;

import java.security.Principal;
import java.sql.Timestamp;

@Data
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    private ModelMapper modelMapper;
	
	@Autowired
	private JWTService jwtService;
	
	@Override
	public String createUser(UserDTO userDTO) {
		UserEntity user = modelMapper.map(userDTO, UserEntity.class);
		final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		user.setCreated_at(new Timestamp(System.currentTimeMillis()));
		user.setUpdated_at(new Timestamp(System.currentTimeMillis()));
        userRepository.save(user);
		return jwtService.generateToken(userDTO);
	}

	@Override
	public String login(UserDTO userDTO) {
		UserEntity user = userRepository.findByEmail(userDTO.getEmail());
		if(user == null) {
			throw new AuthenticationException("error");
		}
		final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		if(!passwordEncoder.matches(userDTO.getPassword(), user.getPassword())) {
			throw new AuthenticationException("error");
		}
		return jwtService.generateToken(userDTO);
	}

	@Override
	public UserResponse me(Principal user) {
		return modelMapper.map(userRepository.findByEmail(user.getName()), UserResponse.class);
	}
}

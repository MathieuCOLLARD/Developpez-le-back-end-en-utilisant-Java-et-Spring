package com.openclassrooms.api.services;

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
import java.util.Optional;

@Data
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    private ModelMapper modelMapper;

	@Autowired
	private JWTService jwtService;

	/**
	 * @param userDTO
	 * @return String
	 */
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

	/**
	 * @param userDTO
	 * @return Optional<String>
	 */
	@Override
	public Optional<String> login(UserDTO userDTO) {
		UserEntity user = userRepository.findByEmail(userDTO.getEmail());
		if(user == null) {
			return Optional.empty();
		}
		final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		if(!passwordEncoder.matches(userDTO.getPassword(), user.getPassword())) {
			return Optional.empty();
		}
		return Optional.of(jwtService.generateToken(userDTO));
	}

	/**
	 * @param user
	 * @return UserResponse
	 */
	@Override
	public UserResponse me(Principal user) {
		return modelMapper.map(userRepository.findByEmail(user.getName()), UserResponse.class);
	}

	/**
	 * @param id
	 * @return UserResponse
	 */
	@Override
	public UserResponse getUser(Long id) {
		return modelMapper.map(userRepository.findById(id).get(), UserResponse.class);
	}
}

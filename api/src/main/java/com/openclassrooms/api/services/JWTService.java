package com.openclassrooms.api.services;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import com.openclassrooms.api.dto.UserDTO;


@Service
public class JWTService {


	private JwtEncoder jwtEncoder;

	public JWTService(JwtEncoder jwtEncoder) {
		this.jwtEncoder = jwtEncoder;
	}

	public String generateToken(UserDTO userDTO) {
        		Instant now = Instant.now();
     		JwtClaimsSet claims = JwtClaimsSet.builder()
              		  .issuer("self")
               		 .issuedAt(now)
              		  .expiresAt(now.plus(1, ChronoUnit.DAYS))
              		  .subject(userDTO.getEmail())
              		  .build();
		JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters.from(JwsHeader.with(MacAlgorithm.HS256).build(), claims);
		return this.jwtEncoder.encode(jwtEncoderParameters).getTokenValue();
	}
}


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

	private JwtDecoder jwtDecoder;

	public JWTService(JwtEncoder jwtEncoder, JwtDecoder jwtDecoder) {
		this.jwtEncoder = jwtEncoder;
		this.jwtDecoder = jwtDecoder;
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

	public String decodeToken(String token) {
		System.out.println(token);
		Jwt jwt = this.jwtDecoder.decode("eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJzZWxmIiwic3ViIjoidGVzdEB0ZXN0LmNvbSIsImV4cCI6MTcwMzM1MTMyNywiaWF0IjoxNzAzMjY0OTI3fQ.fuKlCw_cxVYXNAaVWqL-m3PmicRFyOUweOprMqxgUDg");
		return jwt.getClaims().get("sub").toString();
	}
}


package com.openclassrooms.api.response;

import lombok.Data;

@Data
public class TokenResponse {
	private String token;
	
	public TokenResponse(String token) {
		super();
		this.token = token;
	}
}

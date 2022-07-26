package br.com.wmoddev.examplesecurity.authentication;

import lombok.Getter;

@Getter
public class AuthResponseDTO {
	
	private String accessToken;
	
//	private String refreshToken;

	public AuthResponseDTO(String accessToken) {
		this.accessToken = accessToken;;
	}

}

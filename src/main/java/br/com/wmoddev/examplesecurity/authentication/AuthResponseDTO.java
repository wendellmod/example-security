package br.com.wmoddev.examplesecurity.authentication;

import lombok.Getter;

@Getter
public class AuthResponseDTO {
	
	private String token;

	public AuthResponseDTO(String token) {
		this.token = "Bearer " + token;;
	}

}

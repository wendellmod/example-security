package br.com.wmoddev.examplesecurity.updateuser;

import java.util.UUID;

import br.com.wmoddev.examplesecurity.entity.LoginUser;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @NoArgsConstructor
public class LoginUserDTO {
	
	private UUID id;
	private String username;
	private String password;
	
	public LoginUserDTO(LoginUser lu) {
		this.id = lu.getId();
		this.username = lu.getUsername();
		this.password = lu.getPassword();
	}

}

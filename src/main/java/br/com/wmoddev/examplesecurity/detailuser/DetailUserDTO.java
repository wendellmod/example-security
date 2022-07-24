package br.com.wmoddev.examplesecurity.detailuser;

import java.util.UUID;

import br.com.wmoddev.examplesecurity.entity.LoginUser;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @NoArgsConstructor
public class DetailUserDTO {
	
	private UUID id;
	private String username;
	
	public DetailUserDTO(LoginUser lu) {
		this.id = lu.getId();
		this.username = lu.getUsername();
	}

}

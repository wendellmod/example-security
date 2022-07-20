package br.com.wmoddev.examplesecurity.createuser;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class CreateUserDTO {
	
	@NotBlank private String username;
	@NotBlank private String password;

}

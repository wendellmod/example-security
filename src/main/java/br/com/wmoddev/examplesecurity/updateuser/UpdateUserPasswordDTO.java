package br.com.wmoddev.examplesecurity.updateuser;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @NoArgsConstructor
public class UpdateUserPasswordDTO {
	
	@NotBlank private String password;
	@NotBlank private String newPassword;

}

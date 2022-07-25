package br.com.wmoddev.examplesecurity.authentication;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @NoArgsConstructor
public class AuthRequestDTO {
	
    @NotNull
    @Size(max = 30)
    private String username;

    @NotNull
    @Size(max = 30)
    private String password;
	
	public UsernamePasswordAuthenticationToken build() {
		return new UsernamePasswordAuthenticationToken(username, password);
	}

}

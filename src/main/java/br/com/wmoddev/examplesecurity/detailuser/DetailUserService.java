package br.com.wmoddev.examplesecurity.detailuser;

import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.wmoddev.examplesecurity.config.security.AuthenticatedUser;
import br.com.wmoddev.examplesecurity.entity.LoginUser;

@Service
public class DetailUserService {
	
	private final AuthenticatedUser authenticatedUser;

	public DetailUserService(final AuthenticatedUser authenticatedUser) {
		this.authenticatedUser = authenticatedUser;
	}

	public DetailUserDTO execute(UUID idUser) {
		
		LoginUser loginUser = authenticatedUser.get(idUser);
		
		return new DetailUserDTO(loginUser);
	}

}

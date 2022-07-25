package br.com.wmoddev.examplesecurity.detailuser;

import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.wmoddev.examplesecurity.config.security.AuthenticatedUserService;
import br.com.wmoddev.examplesecurity.entity.LoginUser;

@Service
public class DetailUserService {
	
	private final AuthenticatedUserService authenticatedUserService;

	public DetailUserService(final AuthenticatedUserService authenticatedUserService) {
		this.authenticatedUserService = authenticatedUserService;
	}

	public DetailUserDTO execute(UUID idUser) {
		
		LoginUser loginUser = authenticatedUserService.get(idUser);
		
		return new DetailUserDTO(loginUser);
	}

}

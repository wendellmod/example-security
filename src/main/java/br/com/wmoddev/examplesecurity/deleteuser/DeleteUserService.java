package br.com.wmoddev.examplesecurity.deleteuser;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.wmoddev.examplesecurity.config.security.AuthenticatedUserService;
import br.com.wmoddev.examplesecurity.repository.LoginUserRepository;

@Service
public class DeleteUserService {
	
	private final LoginUserRepository loginUserRepository;
	private final AuthenticatedUserService authenticatedUserService;

	public DeleteUserService(final LoginUserRepository loginUserRepository,
							 final AuthenticatedUserService authenticatedUserService) {
		this.loginUserRepository = loginUserRepository;
		this.authenticatedUserService = authenticatedUserService;
	}
	
	@Transactional
	public void execute(UUID idUser) {
		authenticatedUserService.get(idUser);
		loginUserRepository.deleteRelationshipsByLoginUserId(idUser);
		loginUserRepository.deleteById(idUser);
	}

}

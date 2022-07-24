package br.com.wmoddev.examplesecurity.deleteuser;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.wmoddev.examplesecurity.config.security.AuthenticatedUser;
import br.com.wmoddev.examplesecurity.entity.LoginUser;
import br.com.wmoddev.examplesecurity.repository.LoginUserRepository;

@Service
public class DeleteUserService {
	
	private final LoginUserRepository loginUserRepository;
	private final AuthenticatedUser authenticatedUser;

	public DeleteUserService(final LoginUserRepository loginUserRepository,
							 final AuthenticatedUser authenticatedUser) {
		this.loginUserRepository = loginUserRepository;
		this.authenticatedUser = authenticatedUser;
	}
	
	@Transactional
	public void execute(UUID idUser) {
		authenticatedUser.get(idUser);
		loginUserRepository.deleteRelationshipsByLoginUserId(idUser);
		loginUserRepository.deleteById(idUser);
	}

}

package br.com.wmoddev.examplesecurity.deleteuser;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.wmoddev.examplesecurity.entity.LoginUser;
import br.com.wmoddev.examplesecurity.repository.LoginUserRepository;

@Service
public class DeleteUserService {
	
	private final LoginUserRepository loginUserRepository;

	public DeleteUserService(final LoginUserRepository loginUserRepository) {
		this.loginUserRepository = loginUserRepository;
	}
	
	@Transactional
	public void execute(String idUser) {
		UUID id = UUID.fromString(idUser);
		findById(id);
		loginUserRepository.deleteRelationshipsByLoginUserId(id);
		loginUserRepository.deleteById(id);
	}
	
	private LoginUser findById(UUID id) {
		return loginUserRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

}

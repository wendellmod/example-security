package br.com.wmoddev.examplesecurity.detailuser;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.wmoddev.examplesecurity.entity.LoginUser;
import br.com.wmoddev.examplesecurity.repository.LoginUserRepository;

@Service
public class DetailUserService {
	
	private final LoginUserRepository loginUserRepository;

	public DetailUserService(final LoginUserRepository loginUserRepository) {
		this.loginUserRepository = loginUserRepository;
	}

	public DetailUserDTO execute(String idUser) {
		UUID id = UUID.fromString(idUser);
		
		LoginUser loginUser = getById(id);
		
		return new DetailUserDTO(loginUser);
	}
	
	private LoginUser getById(UUID id) {
		return loginUserRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

}

package br.com.wmoddev.examplesecurity.listusers;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.wmoddev.examplesecurity.entity.LoginUser;
import br.com.wmoddev.examplesecurity.entity.enums.Role;
import br.com.wmoddev.examplesecurity.repository.LoginUserRepository;

@Service
public class ListUsersService {
	
	private final LoginUserRepository loginUserRepository;

	public ListUsersService(final LoginUserRepository loginUserRepository) {
		this.loginUserRepository = loginUserRepository;
	}
	
	@Transactional
	public List<UserDTO> execute(Role role) {
		List<LoginUser> users = getUsers(role);
		return UserDTO.buildList(users);
	}
	
	private List<LoginUser> getUsers(Role role) {
		return (role != null) ? findAll(role) : findAll() ;
	}
	
	private List<LoginUser> findAll() {
		return loginUserRepository.findAll();
	}
	
	private List<LoginUser> findAll(Role role) {
		return loginUserRepository.findAllByAuthoritiesRole(role);
	}

}

package br.com.wmoddev.examplesecurity.config.security;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.wmoddev.examplesecurity.entity.LoginUser;
import br.com.wmoddev.examplesecurity.repository.LoginUserRepository;

@Service
public class AuthenticatedUserService {
	
	private final LoginUserRepository loginUserRepository;
	
	public AuthenticatedUserService(final LoginUserRepository loginUserRepository) {
		this.loginUserRepository = loginUserRepository;
	}
	
	public LoginUser get(UUID idUser) {
		UserDetails authenticatedUser = getAuthenticatedUser();
		LoginUser loginUser = getByUsername(authenticatedUser.getUsername());
		
		if (loginUser.getId().equals(idUser)) return loginUser;
		else throw new ResponseStatusException(HttpStatus.FORBIDDEN);
	}

	private static UserDetails getAuthenticatedUser() {
		Object authenticatedUser = SecurityContextHolder.getContext()
				.getAuthentication()
				.getPrincipal();
		if (authenticatedUser instanceof UserDetails ) return ( (UserDetails) authenticatedUser); 
		else throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
	}
	
	private LoginUser getByUsername(String username) {
		return loginUserRepository.findByUsername(username)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

}

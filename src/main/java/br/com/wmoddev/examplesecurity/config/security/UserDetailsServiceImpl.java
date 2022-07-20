package br.com.wmoddev.examplesecurity.config.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.wmoddev.examplesecurity.entity.LoginUser;
import br.com.wmoddev.examplesecurity.repository.LoginUserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	private final LoginUserRepository loginUserRepository;

	public UserDetailsServiceImpl(final LoginUserRepository loginUserRepository) {
		this.loginUserRepository = loginUserRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		LoginUser loginUser = getByUsername(username);

		return loginUser;
	}
	
	private LoginUser getByUsername(String username) {
		return loginUserRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("Username " + username + " does not exist!"));
	}

}

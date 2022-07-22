package br.com.wmoddev.examplesecurity.config.security;

import javax.transaction.Transactional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.wmoddev.examplesecurity.config.security.util.UserDetailsUtil;
import br.com.wmoddev.examplesecurity.entity.LoginUser;
import br.com.wmoddev.examplesecurity.repository.LoginUserRepository;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
	
	private final LoginUserRepository loginUserRepository;

	public UserDetailsServiceImpl(final LoginUserRepository loginUserRepository) {
		this.loginUserRepository = loginUserRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserDetails loginUser = getByUsername(username);

		return loginUser;
	}
	
	private UserDetails getByUsername(String username) {
		LoginUser lu = loginUserRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("Username " + username + " does not exist!"));
		return UserDetailsUtil.build(lu);
	}

}

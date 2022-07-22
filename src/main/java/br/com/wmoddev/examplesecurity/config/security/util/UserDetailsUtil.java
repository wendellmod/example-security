package br.com.wmoddev.examplesecurity.config.security.util;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import br.com.wmoddev.examplesecurity.entity.Authority;
import br.com.wmoddev.examplesecurity.entity.LoginUser;

@Component
public class UserDetailsUtil {
	
	public static UserDetails build(LoginUser lu) {
		return User.builder()
				.username(lu.getUsername())
				.password(lu.getPassword())
				.authorities(toAuthorities(lu.getAuthorities()))
				.accountExpired(false)
				.accountLocked(false)
				.credentialsExpired(false)
				.disabled(false)
				.build();
	}
	
	private static Collection<? extends GrantedAuthority> toAuthorities(List<Authority> authorities) {
		return authorities.stream()
				.map(auth -> new SimpleGrantedAuthority(auth.getRole().toString()))
				.collect(Collectors.toList());
	}

}

package br.com.wmoddev.examplesecurity.authentication;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.wmoddev.examplesecurity.config.security.jwt.JwtTokenService;

@Service
public class AuthenticationService {
	
	private final AuthenticationManager authenticationManager;
	private final UserDetailsService userDetailsService;
	private final JwtTokenService jwtTokenService;

	public AuthenticationService(final AuthenticationManager authenticationManager,
								 final UserDetailsService userDetailsService,
								 final JwtTokenService jwtTokenService) {
		this.authenticationManager = authenticationManager;
		this.userDetailsService = userDetailsService;
		this.jwtTokenService = jwtTokenService;
	}

	public AuthResponseDTO execute(AuthRequestDTO dto) {
		
      try {
    	  authenticationManager.authenticate(dto.build());
      } catch (final BadCredentialsException ex) {
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
      }
      
      final UserDetails userDetails = userDetailsService.loadUserByUsername(dto.getUsername());
      return new AuthResponseDTO(jwtTokenService.generateToken(userDetails));
		
	}

}

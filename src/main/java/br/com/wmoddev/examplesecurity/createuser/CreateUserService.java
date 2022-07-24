package br.com.wmoddev.examplesecurity.createuser;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.wmoddev.examplesecurity.entity.Authority;
import br.com.wmoddev.examplesecurity.entity.LoginUser;
import br.com.wmoddev.examplesecurity.entity.enums.Role;
import br.com.wmoddev.examplesecurity.repository.AuthorityRepository;
import br.com.wmoddev.examplesecurity.repository.LoginUserRepository;

@Service
public class CreateUserService {
	
	private final LoginUserRepository loginUserRepository;
	private final AuthorityRepository authorityRepository;
	private final PasswordEncoder passwordEncoder;

	public CreateUserService(final LoginUserRepository loginUserRepository,
							 final AuthorityRepository authorityRepository,
						  	 final PasswordEncoder passwordEncoder) {
		this.loginUserRepository = loginUserRepository;
		this.authorityRepository = authorityRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Transactional
	public String execute(CreateUserDTO dto) {
		
		List<Authority> authorities = assignsAuthorities();
		
		LoginUser loginUser = LoginUser.builder()
											.username(dto.getUsername())
											.password(passwordEncoder.encode(dto.getPassword()))
											.disabled(false)
											.authorities(authorities)
											.build();
		LoginUser newLoginUser = create(loginUser);
		
		return newLoginUser.getId().toString();
	}
	
	/**
	 * Assigns only ROLE_USER. For ROLE_ADMIN it is necessary to create in the database.
	 * 
	 * @param authorities
	 * @return list of Autority
	 */
	private List<Authority> assignsAuthorities() {
		Authority a = getAuthorityByName(Role.ROLE_USER);
		
		List<Authority> authorities = new ArrayList<>();
		authorities.add(a);
		
		return authorities;
	}
	
	private LoginUser create(LoginUser lu) {
		return loginUserRepository.save(lu);
	}
	
	private Authority getAuthorityByName(Role role) {
		return authorityRepository.findByRole(role)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Authority " + role.toString() + " does not exist!"));
	}
	
//	insert into examplesec.tb_authority values (gen_random_uuid(), 'ROLE_USER');
//	insert into examplesec.tb_authority values (gen_random_uuid(), 'ROLE_ADMIN');
//	insert into examplesec.tb_authority values (gen_random_uuid(), 'ROLE_APP');

}

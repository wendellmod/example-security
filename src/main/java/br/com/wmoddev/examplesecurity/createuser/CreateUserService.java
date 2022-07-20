package br.com.wmoddev.examplesecurity.createuser;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.wmoddev.examplesecurity.entity.LoginUser;
import br.com.wmoddev.examplesecurity.repository.LoginUserRepository;

@Service
public class CreateUserService {
	
	private final LoginUserRepository loginUserRepository;
	private final PasswordEncoder passwordEncoder;

	public CreateUserService(final LoginUserRepository loginUserRepository,
						  	 final PasswordEncoder passwordEncoder) {
		this.loginUserRepository = loginUserRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public String execute(CreateUserDTO dto) {
		
		LoginUser loginUser = LoginUser.builder()
											.username(dto.getUsername())
											.password(passwordEncoder.encode(dto.getPassword()))
											.build();
		LoginUser newLoginUser = create(loginUser);
		
		return newLoginUser.getId().toString();
	}
	
	private LoginUser create(LoginUser lu) {
		return loginUserRepository.save(lu);
	}

}

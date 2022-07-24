package br.com.wmoddev.examplesecurity.updateuser;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.wmoddev.examplesecurity.config.security.AuthenticatedUser;
import br.com.wmoddev.examplesecurity.entity.LoginUser;
import br.com.wmoddev.examplesecurity.repository.LoginUserRepository;

@Service
public class UpdateUserService {
	
	private final LoginUserRepository loginUserRepository;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticatedUser authenticatedUser;

	public UpdateUserService(final LoginUserRepository loginUserRepository,
							 final PasswordEncoder passwordEncoder,
							 final AuthenticatedUser authenticatedUser) {
		this.loginUserRepository = loginUserRepository;
		this.passwordEncoder = passwordEncoder;
		this.authenticatedUser = authenticatedUser;
	}

	public LoginUserDTO execute(UUID idUser, UpdateUserPasswordDTO dto) {
		LoginUser loginUser = authenticatedUser.get(idUser);
		
		if (dto != null) updatePassword(loginUser, dto);
		else enableOrDisableAccount(loginUser);
		
		LoginUser updatedLoginUser = loginUserRepository.save(loginUser);
		
		return new LoginUserDTO(updatedLoginUser);
	}
	
	private void updatePassword(LoginUser loginUser, UpdateUserPasswordDTO dto) {
		String currentPasswordEncoded = loginUser.getPassword();
		Boolean passwordsMatch = passwordEncoder.matches(dto.getPassword(), currentPasswordEncoded);
		
		if (passwordsMatch) {
			String newPasswordEncoded = passwordEncoder.encode(dto.getNewPassword());
			loginUser.setPassword(newPasswordEncoded);
		}
		else throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Passwords do not match.");
	}
	
	private void enableOrDisableAccount(LoginUser loginUser) {
		loginUser.setDisabled(!loginUser.getDisabled());
	}


}

package br.com.wmoddev.examplesecurity.updateuser;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.wmoddev.examplesecurity.entity.LoginUser;
import br.com.wmoddev.examplesecurity.repository.LoginUserRepository;

@Service
public class UpdateUserService {
	
	private final LoginUserRepository loginUserRepository;
	private final PasswordEncoder passwordEncoder;

	public UpdateUserService(final LoginUserRepository loginUserRepository,
							 final PasswordEncoder passwordEncoder) {
		this.loginUserRepository = loginUserRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public LoginUserDTO execute(String idUser, UpdateUserPasswordDTO dto) {
		UUID id = UUID.fromString(idUser);
		LoginUser loginUser = findById(id);
		
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
	
	private LoginUser findById(UUID id) {
		return loginUserRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

}

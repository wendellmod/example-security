package br.com.wmoddev.examplesecurity.deleteuser;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "users")
public class DeleteUserController {
	
	private final DeleteUserService service;

	public DeleteUserController(final DeleteUserService service) {
		this.service = service;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("{idUser}")
	public ResponseEntity<?> delete(@PathVariable UUID idUser) {
		service.execute(idUser);
		return ResponseEntity.noContent().build();
	}

}

package br.com.wmoddev.examplesecurity.detailuser;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "users")
public class DetailUserController {
	
	private final DetailUserService service;

	public DetailUserController(final DetailUserService service) {
		this.service = service;
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping("{idUser}")
	public ResponseEntity<?> get(@PathVariable UUID idUser) {
		return ResponseEntity.ok(service.execute(idUser));
	}

}

package br.com.wmoddev.examplesecurity.listusers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.wmoddev.examplesecurity.entity.enums.Role;

@RestController
@RequestMapping(value = "users")
public class ListUsersController {
	
	private final ListUsersService service;

	public ListUsersController(final ListUsersService service) {
		this.service = service;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping
	public ResponseEntity<?> getAll(@RequestParam(required = false) Role role) {
		return ResponseEntity.ok(service.execute(role));
	}

}

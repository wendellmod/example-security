package br.com.wmoddev.examplesecurity.createuser;

import static br.com.wmoddev.examplesecurity.util.ResponseEntityUtil.*;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "users")
public class CreateUserController {
	
	private final CreateUserService service;

	public CreateUserController(final CreateUserService service) {
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<?> create(@RequestBody @Valid CreateUserDTO dto) {
		return ResponseEntity
				.created(uriGenerate(service.execute(dto)))
				.body(null);
	}

}

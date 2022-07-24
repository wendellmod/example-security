package br.com.wmoddev.examplesecurity.detailuser;

import org.springframework.http.ResponseEntity;
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
	
	@GetMapping("{idUser}")
	public ResponseEntity<?> get(@PathVariable String idUser) {
		return ResponseEntity.ok(service.execute(idUser));
	}

}

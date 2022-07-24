package br.com.wmoddev.examplesecurity.deleteuser;

import org.springframework.http.ResponseEntity;
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
	
	@DeleteMapping("{idUser}")
	public ResponseEntity<?> delete(@PathVariable String idUser) {
		service.execute(idUser);
		return ResponseEntity.noContent().build();
	}

}

package br.com.wmoddev.examplesecurity.updateuser;

import static br.com.wmoddev.examplesecurity.util.ResponseEntityUtil.*;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "users")
public class UpdateUserController {
	
	private final UpdateUserService service;

	public UpdateUserController(final UpdateUserService service) {
		this.service = service;
	}
	
	@PatchMapping("{idUser}/update-password")
	public ResponseEntity<?> update(@PathVariable String idUser,
									@RequestBody(required = true) @Valid UpdateUserPasswordDTO dto) {
		return ResponseEntity.created(uriGenerate(idUser)).body(service.execute(idUser, dto));
	}
	
	@PatchMapping("{idUser}/enable-or-disable-account")
	public ResponseEntity<?> update(@PathVariable String idUser) {
		service.execute(idUser, null);
		return ResponseEntity.noContent().build();
	}

}

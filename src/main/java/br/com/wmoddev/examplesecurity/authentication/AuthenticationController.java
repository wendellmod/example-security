package br.com.wmoddev.examplesecurity.authentication;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
	
	private final AuthenticationService service;
	
    public AuthenticationController(AuthenticationService service) {
		this.service = service;
	}

	@PostMapping("/authenticate")
    public ResponseEntity<AuthResponseDTO> authenticate(@RequestBody @Valid final AuthRequestDTO dto) {
    	return ResponseEntity.ok(service.execute(dto));
    }

}

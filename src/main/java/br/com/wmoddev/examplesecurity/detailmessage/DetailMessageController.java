package br.com.wmoddev.examplesecurity.detailmessage;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "messages")
public class DetailMessageController {
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping("{id}")
	public ResponseEntity<String> helloWorld(@PathVariable String id) {
		
		if (id.equalsIgnoreCase("hello")) {
			return ResponseEntity.ok("Hello World");
		}
		
		return ResponseEntity.badRequest().body("error");
	}

}

package br.com.wmoddev.examplesecurity.listusers;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import br.com.wmoddev.examplesecurity.entity.Authority;
import br.com.wmoddev.examplesecurity.entity.LoginUser;
import br.com.wmoddev.examplesecurity.entity.enums.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @NoArgsConstructor
public class UserDTO {
	
	private UUID id;
	private String username;
	private List<Role> authorities;
	
	public UserDTO(LoginUser lu) {
		this.id = lu.getId();
		this.username = lu.getUsername();
		this.authorities = lu.getAuthorities().stream()
				.map(Authority::getRole)
				.collect(Collectors.toList());
	}
	
	public static List<UserDTO> buildList(List<LoginUser> users) {
		return users.stream()
				.map(UserDTO::new)
				.collect(Collectors.toList());
	}

}

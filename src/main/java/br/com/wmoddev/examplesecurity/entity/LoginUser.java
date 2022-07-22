package br.com.wmoddev.examplesecurity.entity;

import java.util.List;
import java.util.UUID;

import javax.persistence.*;

import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@Table(name = "tb_login_user", schema = "examplesec")
public class LoginUser {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Column(nullable = false, unique = true)
	private String username;
	
	@Column(nullable = false)
	private String password;
	
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Column(nullable = false)
    @JoinTable(name = "tb_roles_login_user", schema = "examplesec",
            joinColumns = @JoinColumn(name = "login_user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Authority> authorities;
	
	@Deprecated public LoginUser() {}
	
	@Builder
	public LoginUser(UUID id,
					 String username,
					 String password,
					 List<Authority> authorities) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.authorities = authorities;
	}

}

package br.com.wmoddev.examplesecurity.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.wmoddev.examplesecurity.entity.enums.Role;
import lombok.Builder;
import lombok.Getter;

@Entity @Getter
@Table(name = "tb_authority", schema = "examplesec")
public class Authority {
	
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private Role role;
    
	@Deprecated public Authority() {}
	
	@Builder
	public Authority(UUID id,
				Role userRole) {
		this.id = id;
		this.role = userRole;
	}

}

package br.com.wmoddev.examplesecurity.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.wmoddev.examplesecurity.entity.Authority;
import br.com.wmoddev.examplesecurity.entity.enums.Role;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, UUID> {
	
	Optional<Authority> findByRole(Role role);
	
}

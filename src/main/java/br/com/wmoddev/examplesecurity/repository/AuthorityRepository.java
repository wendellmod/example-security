package br.com.wmoddev.examplesecurity.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.wmoddev.examplesecurity.entity.Authority;
import br.com.wmoddev.examplesecurity.entity.enums.Role;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, UUID> {
	
//	@Query("SELECT a FROM Authority a WHERE a.role = ?1")
//	Optional<Authority> findByRoleName(String role);
	
	@Query("SELECT a FROM Authority a WHERE a.role = ?1")
	Optional<Authority> findByRole(Role role);
	
}

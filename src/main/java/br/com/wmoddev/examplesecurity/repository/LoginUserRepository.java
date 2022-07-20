package br.com.wmoddev.examplesecurity.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.wmoddev.examplesecurity.entity.LoginUser;

@Repository
public interface LoginUserRepository extends JpaRepository<LoginUser, UUID>{
	
	Optional<LoginUser> findByUsername(String username);
	
}

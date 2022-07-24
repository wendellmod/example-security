package br.com.wmoddev.examplesecurity.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.wmoddev.examplesecurity.entity.LoginUser;
import br.com.wmoddev.examplesecurity.entity.enums.Role;

@Repository
public interface LoginUserRepository extends JpaRepository<LoginUser, UUID>{
	
	Optional<LoginUser> findByUsername(String username);
	List<LoginUser> findAllByAuthoritiesRole(Role role);
	
	@Modifying
	@Query(value = "delete from examplesec.tb_roles_login_user rel "
				 + "where rel.login_user_id = :id", 
		   nativeQuery = true)
	void deleteRelationshipsByLoginUserId(@Param("id") UUID id);
	
}

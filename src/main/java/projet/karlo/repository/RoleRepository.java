package projet.karlo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import projet.karlo.model.Role;

public interface RoleRepository extends JpaRepository<Role,String>{
    
}

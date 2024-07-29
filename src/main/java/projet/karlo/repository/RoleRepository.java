package projet.karlo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import projet.karlo.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role,String>{
    
    Role findByLibelle(String libelle);
}

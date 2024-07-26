package projet.karlo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import projet.karlo.model.Marque;
import projet.karlo.model.Role;

public interface MarqueRepository extends JpaRepository<Marque,String>{
    
    Marque findByNomMarque(String nom);
}

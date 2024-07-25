package projet.karlo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import projet.karlo.model.Marque;

public interface MarqueRepository extends JpaRepository<Marque,String>{
    
}

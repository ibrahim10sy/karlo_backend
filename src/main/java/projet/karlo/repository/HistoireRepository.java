package projet.karlo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import projet.karlo.model.Historique;

public interface HistoireRepository extends JpaRepository<Historique,String> {
    
}

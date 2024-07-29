package projet.karlo.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import projet.karlo.model.Historique;

@Repository
public interface HistoriqueRepository extends JpaRepository<Historique,String>{
    Historique findByIdHistorique(String id);
}

package projet.karlo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import projet.karlo.model.VoitureVendre;

@Repository
public interface VoitureVendreRepository extends JpaRepository<VoitureVendre,String>{

    List<VoitureVendre> findByAnnee(String annee);
    List<VoitureVendre> findByTypeBoite(String typeBoite);
    List<VoitureVendre> findByNbreView();
    List<VoitureVendre> findByTypeReservoir_idTypeReservoir(String id);
    List<VoitureVendre> findByTypeVoiture_idTypeVoiture(String id);
    List<VoitureVendre> findByUser_idUser(String id);
}

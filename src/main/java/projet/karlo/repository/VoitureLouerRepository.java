package projet.karlo.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import projet.karlo.model.VoitureLouer;

@Repository
public interface VoitureLouerRepository extends JpaRepository<VoitureLouer,String>{

    List<VoitureLouer> findByAnnee(String annee);
    List<VoitureLouer> findByTypeBoite(String typeBoite);
    // List<VoitureLouer> findByNbreView();
    List<VoitureLouer> findByTypeReservoir_idTypeReservoir(String id);
    List<VoitureLouer> findByTypeVoiture_idTypeVoiture(String id);
    List<VoitureLouer> findByUser_idUser(String id);

}

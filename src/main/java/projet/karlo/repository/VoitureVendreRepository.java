package projet.karlo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import projet.karlo.model.VoitureVendre;

@Repository
public interface VoitureVendreRepository extends JpaRepository<VoitureVendre,String>{
    long count(); // Méthode pour compter le nombre total des voitures à vendre


    List<VoitureVendre> findByAnnee(String annee);
    List<VoitureVendre> findByTypeBoite(String typeBoite);
    // List<VoitureVendre> findByNbreView();
    List<VoitureVendre> findByTypeReservoir_idTypeReservoir(String id);
    List<VoitureVendre> findByTypeVoiture_idTypeVoiture(String id);
    // List<VoitureVendre> findByNbreView();

    List<VoitureVendre> findByTypeReservoir_NomTypeReservoir(String nom);
    List<VoitureVendre> findByTypeVoiture_NomTypeVoiture(String nom);
    List<VoitureVendre> findByUser_idUser(String id);
    List<VoitureVendre> findByMarque_NomMarque(String nom);

     // Pour récupérer toutes les voitures triées par nombre de vues en ordre décroissant
    List<VoitureVendre> findAllByOrderByNbreViewDesc();

     // Pour récupérer toutes les voitures triées par prix augmenté en ordre décroissant
    List<VoitureVendre> findAllByOrderByPrixAugmenteDesc();

     // Pour récupérer toutes les voitures triées par prix augmenté en ordre croissant
    List<VoitureVendre> findAllByOrderByPrixAugmenteAsc();


}

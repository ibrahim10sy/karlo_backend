package projet.karlo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import projet.karlo.model.Vente;

public interface VenteRepository extends JpaRepository<Vente,String> {
    
    List<Vente> findByNomClient(String nom);

}

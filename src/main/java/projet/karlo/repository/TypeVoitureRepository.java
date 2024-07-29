package projet.karlo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import projet.karlo.model.TypeVoiture;

@Repository
public interface TypeVoitureRepository extends JpaRepository<TypeVoiture,String> {
    TypeVoiture findByIdTypeVoiture(String id);
    TypeVoiture findByNomTypeVoiture(String id);
}

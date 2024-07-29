package projet.karlo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import projet.karlo.model.TypeReservoir;

@Repository
public interface TypeReservoirRepository  extends JpaRepository<TypeReservoir,String>{
    
    TypeReservoir findByIdTypeReservoir(String id);
    TypeReservoir findByNomTypeReservoir(String id);
}

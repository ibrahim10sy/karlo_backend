package projet.karlo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import projet.karlo.model.TypeTransaction;

@Repository
public interface  TypeTransactionRepository  extends JpaRepository<TypeTransaction , String>{
    
    TypeTransaction findByLibelle(String libelle);

    List<TypeTransaction> findAllByLibelle(String libelle);
}

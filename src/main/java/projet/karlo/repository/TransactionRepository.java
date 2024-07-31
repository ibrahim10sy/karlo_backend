package projet.karlo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import projet.karlo.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {
    
    List<Transaction> findByTypeTransaction_Libelle(String libelle);
    List<Transaction> findByDateTransaction(String date);
}

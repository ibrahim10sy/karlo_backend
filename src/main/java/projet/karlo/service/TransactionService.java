package projet.karlo.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import projet.karlo.exception.NoContentException;
import projet.karlo.model.Transaction;
import projet.karlo.model.TypeTransaction;
import projet.karlo.model.User;
import projet.karlo.repository.TransactionRepository;
import projet.karlo.repository.TypeTransactionRepository;
import projet.karlo.repository.UserRepository;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transRepository;
    @Autowired
    TypeTransactionRepository typeRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    IdGenerator idGenerator ;
    @Autowired
    HistoriqueService historiqueService;

    public Transaction createTransaction(Transaction transaction) {
        User user = userRepository.findByIdUser(transaction.getUser().getIdUser());

        TypeTransaction type = typeRepository.findById(transaction.getTypeTransaction().getIdTypeTransaction()).orElseThrow();

        if(type == null)
            throw new EntityNotFoundException("Type de transaction non trouvée");
    
        if(user == null)
            throw new EntityNotFoundException("User non trouvée");

        String idcodes = idGenerator.genererCode();
        String pattern = "yyyy-MM-dd HH:mm";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime now = LocalDateTime.now();
        String formattedDateTime = now.format(formatter);
        transaction.setIdTransaction(idcodes);
        transaction.setDateTransaction(formattedDateTime);
        historiqueService.createHistorique("Ajout " + transaction.getDescription());
        return transRepository.save(transaction);
    }

    public Transaction updateTrans(Transaction transaction , String id){
        Transaction t = transRepository.findById(id).orElseThrow();

        // t.setDateTransaction(transaction.getDateTransaction());
        t.setDescription(transaction.getDescription());

        String pattern = "yyyy-MM-dd HH:mm";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime now = LocalDateTime.now();
        String formattedDateTime = now.format(formatter);
        t.setDateModif(formattedDateTime);

        if(transaction.getTypeTransaction() != null){
            t.setTypeTransaction(transaction.getTypeTransaction());
        }

        historiqueService.createHistorique("Modificatipn " + t.getDescription());
        return transRepository.save(t);
    }

    public List<Transaction> getAllTransactions() {
        List<Transaction> transactions = transRepository.findAll();

        if(transactions.isEmpty())
            throw new IllegalStateException("No transactions");
        
        transactions.sort(Comparator.comparing(Transaction::getDateTransaction));
        return transactions;
    }

    public List<Transaction> getAllTransactionsByDate(String date) {
        List<Transaction> transactions = transRepository.findByDateTransaction(date);

        if(transactions.isEmpty())
            throw new IllegalStateException("No transactions");
        
        transactions.sort(Comparator.comparing(Transaction::getDateTransaction));
        return transactions;
    }

    public List<Transaction> getAllTransactionsByType(String libelle) {
        List<Transaction> transactions = transRepository.findByTypeTransaction_Libelle(libelle);

        if(transactions.isEmpty())
            throw new IllegalStateException("No transactions");
    
        transactions.sort(Comparator.comparing(Transaction::getDateTransaction));
        return transactions;
    }

    public String deleteTransaction(String id){
        Transaction t = transRepository.findById(id).orElseThrow();
        historiqueService.createHistorique("Suppression " + t.getDescription());
        transRepository.delete(t);

        return "Supprimé avec succèss"; 
    }
}

package projet.karlo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import projet.karlo.model.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact,String> {
    
}

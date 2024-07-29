package projet.karlo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import projet.karlo.model.User;

@Repository
public interface UserRepository extends JpaRepository< User,String> {
    
    User findByIdUser(String id);
    User findByEmail(String email);
}

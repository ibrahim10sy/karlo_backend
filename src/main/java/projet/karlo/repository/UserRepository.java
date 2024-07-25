package projet.karlo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import projet.karlo.model.User;

public interface UserRepository extends JpaRepository< User,String> {
    
}

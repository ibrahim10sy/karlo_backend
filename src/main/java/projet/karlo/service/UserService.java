package projet.karlo.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import projet.karlo.model.User;
import projet.karlo.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    UserRepository userRepository;
    @Autowired
    IdGenerator idGenerator ;

    public User createUser(User user){

        String idcodes = idGenerator.genererCode();
        String pattern = "yyyy-MM-dd HH:mm";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime now = LocalDateTime.now();
        String formattedDateTime = now.format(formatter);
        user.setIdUser(idcodes);
        user.setDateAjout(formattedDateTime);
        
        return userRepository.save(user);
    }


    public User updateUser(User user, String id) {
        User u = userRepository.findById(id).orElseThrow(() -> new IllegalStateException("User non trouvé") );

        u.setNomUser(user.getNomUser());
        u.setAdresse(user.getAdresse());
        u.setEmail(user.getEmail());
        u.setTelephone(user.getTelephone());
        
        return userRepository.save(u);
    }

    public List<User> getAllUser() {
        List<User> users = userRepository.findAll();
    
        if (users.isEmpty())
            throw new EntityNotFoundException("Aucune utilisateur trouvée");
    
        users.sort(Comparator.comparing(User::getNomUser));
        
        return users;
    }

    public String deleteUser(String idUser){
        User user = userRepository.findById(idUser).orElseThrow(() -> new IllegalStateException("User non trouvé") );

        if(user == null)
            throw new IllegalStateException("User not found");

        userRepository.delete(user);
        return "Utilisateur supprimé avec succèss";
    }

}

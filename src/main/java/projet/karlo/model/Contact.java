package projet.karlo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Contact {
    
    @Id
    private String  idContact;

    @Column(nullable = false)
    private String nomComplet;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String telephone;
  
    @Column(nullable = false)
    private String dateAjout;

    @Column(nullable = false)
    private String message;
}

package projet.karlo.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class User {
    
    @Id
    private String idUser;

    @Column(nullable = false)
    private String nomUser;

    @Column(nullable = true)
    private String email;

    @Column(nullable = true)
    private String password;

    @Column(nullable = false)
    private String adresse;

    @Column(nullable = false)
    private String telephone;

    @Column(nullable = false)
    private String dateAjout;

    private Boolean statut = false;

    @OneToMany
    (mappedBy = "user")
    @JsonIgnore
    private List<VoitureLouer> voitureLouers;

    @OneToMany
    (mappedBy = "user")
    @JsonIgnore
    private List<VoitureVendre> voitureVendre;

    @ManyToOne
    Role role;

    @OneToMany
    (mappedBy = "user")
    @JsonIgnore
    private List<Transaction> transactions;
}

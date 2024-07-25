package projet.karlo.model;

import lombok.Data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Data
public class Marque {
    
    @Id
    private String idMarque;

    @Column(nullable = false)
    private String nomMarque;

    @Column(nullable = false)
    private String logo;
    
    @OneToMany
    (mappedBy = "marque")
    @JsonIgnore
    private List<VoitureLouer> voitureLouers;

    @OneToMany
    (mappedBy = "marque")
    @JsonIgnore
    private List<VoitureVendre> voitureVendre;
}

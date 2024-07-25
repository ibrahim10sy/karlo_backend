package projet.karlo.model;

import lombok.Data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;


@Entity
@Data
public class TypeVoiture {
    
    @Id
    private String idTypeVoiture;

    @Column(nullable = false)
    private String nomTypeVoiture;

    @Column(nullable = false)
    private String description;

    @OneToMany
    (mappedBy = "type_voiture")
    @JsonIgnore
    private List<VoitureLouer> voitureLouers;

    @OneToMany
    (mappedBy = "type_voiture")
    @JsonIgnore
    private List<VoitureVendre> voitureVendre;
}

package projet.karlo.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class TypeReservoire {
    
    @Id
    private String idTypeReservoir;

    @Column(nullable = false)
    private String nomTypeReservoir;

    @Column(nullable = false)
    private String description;

    @OneToMany
    (mappedBy = "type_reservoire")
    @JsonIgnore
    private List<VoitureLouer> voitureLouers;

    @OneToMany
    (mappedBy = "type_reservoire")
    @JsonIgnore
    private List<VoitureVendre> voitureVendre;
}

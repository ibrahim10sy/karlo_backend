package projet.karlo.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Reservation {

    @Id
    private String idReservation;

    @Column(nullable = false)
    private String dateDebut;

    @Column(nullable = false)
    private String dateFin;

    @Column(nullable = false)
    private String nomClient;

    @Column(nullable = false)
    private String telephone;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String nomPropritaire;

    @OneToMany(mappedBy = "reservation")
    private List<Image> images;
}

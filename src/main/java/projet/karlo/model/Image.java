package projet.karlo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String imageName;

    @Column(nullable = false)
    private String imagePath;

    @ManyToOne
    @JoinColumn(name = "id_voitureVendre", nullable = true)
    private VoitureLouer voitureLouer;

    @ManyToOne
    @JoinColumn(name = "id_voitureLouer", nullable = true)
    private VoitureVendre voitureVendre;

    @ManyToOne
    @JoinColumn(name = "id_reservation", nullable = true)
    private Reservation reservation;
}

package projet.karlo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Historique {
    @Id
    private String idHistorique;

    @Column(nullable = false)
    private String dateHistorique;

    @Column(nullable = false)
    private String description;
}

package projet.karlo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Historique {
    @Id
    private String idHistorique;
}

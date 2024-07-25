package projet.karlo.model;

import jakarta.persistence.*;
import jakarta.persistence.ManyToOne;
import lombok.Data;


@Entity
@Data
public class VoitureVendre {
    
    @Id
    private String idVoiture;

    @Column(nullable = false)
    private String matricule;

    @Column(nullable = false)
    private String modele;

    @Column(nullable = false)
    private String annee;

    @Column(nullable = false)
    private String typeBoite;

    @Column(nullable = false)
    private String dateAjout;
    
    @Column(nullable = true)
    private String dateModif;

    @Column(nullable = false)
    private int nbreView;

    @Column(nullable = false)
    private int nbPortiere;

    @Column(nullable = false)
    private int prixProprietaire;

    @Column(nullable = false)
    private int prixAugmente;

    @ManyToOne
    Marque marque;

    @ManyToOne
    TypeVoiture typeVoiture;

    @ManyToOne
    TypeReservoire typeReservoire;
}

package projet.karlo.model;


import jakarta.persistence.*;
import lombok.Data;


@Data
public class Alerte {
    
      // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
     private String id;

    @Column(nullable = true)
    private String sujet;

    @Column(nullable = true)
    private String email;

    @Column(length = 2000, nullable = false)
    private String message;

    private String dateAjout;

 

    // Constructeur par défaut
    public Alerte() {
    }

    public Alerte(String email, String message, String sujet){
     this.email = email;
     this.message = message;
     this.sujet = sujet;
    }

   

    // public Alerte(String email, String message, String sujet, Acteur acteur){
    //   this.email = email;
    //   this.message = message;
    //   this.sujet = sujet;
    //   this.acteur = acteur;
    // }

  public Alerte(String email, String message){

    this.email = email;
    this.message = message;

  }



}

package projet.karlo.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class TypeTransaction {

    @Id
    private String idTypeTransaction;

    @Column(nullable = false)
    private String libelle;

    @OneToMany
    (mappedBy = "typeTransaction")
    @JsonIgnore
    private List<Transaction> transactions;
}

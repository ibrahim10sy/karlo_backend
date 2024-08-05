package projet.karlo.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Role {


    @Id
    private String idRole;

    @Column(nullable = false)
    private String libelle;

    @Column(nullable = false)
    private String description;

    @OneToMany
    (mappedBy = "role")
    @JsonIgnore
    private List<User> user;
}

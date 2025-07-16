package com.example.foyer.DAO.Entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Entity
@Table(name = "T_UNIVERSITE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Universite implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idUniversite;

    String nomUniversite;

    String adresse;

    // Relation OneToOne vers Foyer, propriétaire de la relation
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_foyer") // clé étrangère dans Universite pointant vers Foyer
            Foyer foyer;
}

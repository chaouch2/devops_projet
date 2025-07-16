package com.example.foyer.DAO.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Bloc implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name="idBloc")
    Long idBloc; // Clé primaire
    String nomBloc;
    Long capaciteBloc;
    @OneToMany(mappedBy = "bloc",fetch = FetchType.EAGER)
    //  @JsonIgnore
    List<Chambre> chambres;
    @ManyToOne
    @JsonIgnore
    Foyer foyer;







}
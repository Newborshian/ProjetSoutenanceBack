package com.example.backproxibanque.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "compteepargne")
@AllArgsConstructor
@NoArgsConstructor
public class CompteEpargne {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "numerodecompte")
    private Integer numeroDeCompte;
    @Column(name = "solde")
    private Double solde;
    @Column(name = "createdat")
    private String createdAt;
    @Column(name = "tauxinteret")
    private Double tauxInteret;
    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;

}

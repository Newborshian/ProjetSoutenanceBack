package com.example.backproxibanque.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "comptecourant")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompteCourant {

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
    @Column(name = "card")
    private String card;
    @Column(name = "overdraft")
    private Double overdraft;
    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;
}

package com.example.backproxibanque.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateCompteBancaireModel {
    private Integer numerodecompte;
    private Boolean isEpargne;
    private Double overDraft;
    private Double tauxInteret;
    private String card;
    private Double solde;
    private String createdat;
    private Integer id_client;
}

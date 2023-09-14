package com.example.backproxibanque.services;

import com.example.backproxibanque.dtos.ClientDto;
import com.example.backproxibanque.dtos.CompteBancaireDto;
import com.example.backproxibanque.entities.CompteCourant;
import com.example.backproxibanque.entities.CompteEpargne;

import java.util.List;

public interface CompteBancaireServices {
    CompteBancaireDto compteCourantToDto(CompteCourant compteCourant);
    CompteBancaireDto compteEpargneToDto(CompteEpargne compteCourant);
    List<CompteBancaireDto> getAllCompteBancaire();
}

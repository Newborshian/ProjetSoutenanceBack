package com.example.backproxibanque.services;

import com.example.backproxibanque.dtos.ClientDto;
import com.example.backproxibanque.entities.CompteCourant;
import com.example.backproxibanque.entities.CompteEpargne;

import java.util.List;

public interface CompteBancaireServices {
    CompteBancaireDto CompteCourantToDto(CompteCourant compteCourant);
    CompteBancaireDto CompteEpargneToDto(CompteEpargne compteCourant);
    List<CompteBancaireDto> getAllCompteBancaire();
}

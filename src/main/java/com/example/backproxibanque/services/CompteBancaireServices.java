package com.example.backproxibanque.services;

import com.example.backproxibanque.dtos.ClientDto;
import com.example.backproxibanque.dtos.CompteBancaireDto;
import com.example.backproxibanque.entities.CompteCourant;
import com.example.backproxibanque.entities.CompteEpargne;
import com.example.backproxibanque.models.CreateCompteBancaireModel;

import java.util.List;

public interface CompteBancaireServices {
    CompteBancaireDto compteCourantToDto(CompteCourant compteCourant);
    CompteBancaireDto compteEpargneToDto(CompteEpargne compteCourant);
    List<CompteBancaireDto> getAllCompteBancaire();
    void  deleteCompteCourantById(Integer id );
    void  deleteCompteEpargneById(Integer id );
    CompteCourant updateCompteCourant(Integer id, CompteBancaireDto compteBancaireDto);
    CompteEpargne updateCompteEpargne(Integer id, CompteBancaireDto compteBancaireDto);
    CompteBancaireDto getByIdCompteCourant (Integer id);
    CompteBancaireDto getByIdCompteEpargne(Integer id);
    List<CompteBancaireDto> getByIdClientComptesBancaires(Integer clientDto);
    CompteBancaireDto createBankAccount(CreateCompteBancaireModel createCompteBancaireModel);


    void virementCompteCourantToCompteEpargne(Integer idCompteCourant, Integer idCompteEpargne, Double somme);

    void virementCompteCourantToCourant(Integer idCompteCourantDebiteur, Integer idCompteCourantCrediteur, Double somme);

    void virementCompteEpargneToCompteCourant(Integer idCompteEpargne, Integer idCompteCourant, Double somme);

    void virementCompteEpargneToCompteEpargne(Integer idCompteEpargneDebiteur, Integer idCompteEpargneCrediteur, Double somme);
}

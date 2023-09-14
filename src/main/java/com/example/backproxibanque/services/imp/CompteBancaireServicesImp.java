package com.example.backproxibanque.services.imp;

import com.example.backproxibanque.dtos.CompteBancaireDto;
import com.example.backproxibanque.entities.CompteCourant;
import com.example.backproxibanque.entities.CompteEpargne;
import com.example.backproxibanque.services.CompteBancaireServices;

import java.util.List;

public class CompteBancaireServicesImp implements CompteBancaireServices {
    @Override
    public CompteBancaireDto CompteCourantToDto(CompteCourant compteCourant) {
        return null;
    }

    @Override
    public CompteBancaireDto CompteEpargneToDto(CompteEpargne compteCourant) {
        return null;
    }

    @Override
    public List<CompteBancaireDto> getAllCompteBancaire() {
        return null;
    }
}

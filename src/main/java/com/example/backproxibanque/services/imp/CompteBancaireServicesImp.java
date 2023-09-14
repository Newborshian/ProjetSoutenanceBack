package com.example.backproxibanque.services.imp;

import com.example.backproxibanque.dtos.ClientDto;
import com.example.backproxibanque.dtos.CompteBancaireDto;
import com.example.backproxibanque.entities.Client;
import com.example.backproxibanque.entities.CompteCourant;
import com.example.backproxibanque.entities.CompteEpargne;
import com.example.backproxibanque.enums.TypeDeCompte;
import com.example.backproxibanque.repositories.CompteCourantRepository;
import com.example.backproxibanque.repositories.CompteEpargneRepository;
import com.example.backproxibanque.services.CompteBancaireServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CompteBancaireServicesImp implements CompteBancaireServices {

    @Autowired
    private CompteCourantRepository compteCourantRepository;

    @Autowired
    private CompteEpargneRepository compteEpargneRepository;
    @Override
    public CompteBancaireDto compteCourantToDto(CompteCourant compteCourant) {
        CompteBancaireDto compteBancaireDto = new CompteBancaireDto();
        compteBancaireDto.setId(compteCourant.getId());
        compteBancaireDto.setNumerodecompte(compteCourant.getNumeroDeCompte());
        compteBancaireDto.setTypeDeCompte(TypeDeCompte.COURANT.toString());
        compteBancaireDto.setSolde(compteCourant.getSolde());
        compteBancaireDto.setOverDraft(compteCourant.getOverdraft());
        compteBancaireDto.setNameClient(compteCourant.getClient().getLastname() + " " + compteCourant.getClient().getFirstname());
        return compteBancaireDto;
    }

    @Override
    public CompteBancaireDto compteEpargneToDto(CompteEpargne compteEpargne) {
        CompteBancaireDto compteBancaireDto = new CompteBancaireDto();
        compteBancaireDto.setId(compteEpargne.getId());
        compteBancaireDto.setNumerodecompte(compteEpargne.getNumeroDeCompte());
        compteBancaireDto.setTypeDeCompte(TypeDeCompte.EPARGNE.toString());
        compteBancaireDto.setSolde(compteEpargne.getSolde());
        compteBancaireDto.setTauxInteret(compteEpargne.getTauxInteret());
        compteBancaireDto.setNameClient(compteEpargne.getClient().getLastname() + " " + compteEpargne.getClient().getFirstname());
        return compteBancaireDto;
    }

    @Override
    public List<CompteBancaireDto> getAllCompteBancaire() {
        List<CompteBancaireDto> compteBancaireDtoList = new ArrayList<>();
        List<CompteCourant> compteCourantList = compteCourantRepository.findAll();
        List<CompteEpargne> compteEpargneList = compteEpargneRepository.findAll();

        if (!compteEpargneList.isEmpty() || !compteCourantList.isEmpty()){
            if (!compteCourantList.isEmpty()){
            for(CompteCourant  compteCourant : compteCourantList ){
                compteBancaireDtoList.add(compteCourantToDto(compteCourant)) ;
            }}
            if (!compteEpargneList.isEmpty()) {
                for(CompteEpargne  compteEpargne : compteEpargneList ){
                    compteBancaireDtoList.add(compteEpargneToDto(compteEpargne)) ;
            }}
            return compteBancaireDtoList;
        }
        else throw new RuntimeException("Veuillez affilier des comptes bancaires à ce client");
    }

    @Override
    public void deleteCompteCourantById(Integer id) {
        if (compteCourantRepository.findById(id).isPresent()){
            compteCourantRepository.deleteById(id);}
        else{
            throw new RuntimeException("l'identifiant du compte courant n'a pas été trouvé");
        }
    }
    public void deleteCompteEpargneById(Integer id) {
        if (compteEpargneRepository.findById(id).isPresent()){
            compteEpargneRepository.deleteById(id);}
        else{
            throw new RuntimeException("l'identifiant du compte Epargne n'a pas été trouvé");
        }
    }

    public CompteCourant updateCompteCourant(Integer id, CompteBancaireDto compteBancaireDto) {
        CompteCourant compteCourantUpdate = compteCourantRepository.findById(id).get();
        if (compteCourantRepository.existsById(id)) {
            compteCourantUpdate.setSolde(compteBancaireDto.getSolde());
            compteCourantUpdate.setOverdraft(compteBancaireDto.getOverDraft());

            return compteCourantRepository.saveAndFlush(compteCourantUpdate);
        }
        else {
            throw new RuntimeException("L'identifiant n'existe pas");
        }

    }
    @Override
    public CompteEpargne updateCompteEpargne(Integer id, CompteBancaireDto compteBancaireDto) {
        CompteEpargne compteEpargneUpdate = compteEpargneRepository.findById(id).get();
        if (compteCourantRepository.existsById(id)) {
            compteEpargneUpdate.setSolde(compteBancaireDto.getSolde());
            compteEpargneUpdate.setTauxInteret(compteBancaireDto.getTauxInteret());

            return compteEpargneRepository.saveAndFlush(compteEpargneUpdate);
        }

        else {
            throw new RuntimeException("L'identifiant n'existe pas");
        }

    }
    }



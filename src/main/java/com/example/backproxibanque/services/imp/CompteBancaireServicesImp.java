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
import jakarta.transaction.Transactional;
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
        if (compteEpargneRepository.existsById(id)) {
            compteEpargneUpdate.setSolde(compteBancaireDto.getSolde());
            compteEpargneUpdate.setTauxInteret(compteBancaireDto.getTauxInteret());

            return compteEpargneRepository.saveAndFlush(compteEpargneUpdate);
        }

        else {
            throw new RuntimeException("L'identifiant n'existe pas");
        }

    }

    @Override
    public CompteBancaireDto getByIdCompteCourant(Integer id) {
        CompteCourant compteCourant = compteCourantRepository.findById(id).get();
        if (compteCourantRepository.findById(id).isPresent()) {
            return compteCourantToDto(compteCourant);
        }  else {
            throw new RuntimeException("l'identifiant du compte courant n'a pas été trouvé");
        }
    }

    @Override
    public CompteBancaireDto getByIdCompteEpargne(Integer id) {
        CompteEpargne compteEpargne = compteEpargneRepository.findById(id).get();
        if (compteEpargneRepository.findById(id).isPresent()) {
            return compteEpargneToDto(compteEpargne);
        }  else {
            throw new RuntimeException("l'identifiant du compte epargne n'a pas été trouvé");
        }
    }

    @Override
    @Transactional
    public void virementCompteCourantToCompteEpargne(Integer idCompteCourant, Integer idCompteEpargne, Double somme) {

        if(compteCourantRepository.existsById(idCompteCourant) && compteCourantRepository.existsById(idCompteEpargne)) {

            CompteCourant compteCourantDebiteur = compteCourantRepository.findById(idCompteCourant).get();
            CompteEpargne compteEpargneCrediteur = compteEpargneRepository.findById(idCompteEpargne).get();
            if (compteCourantDebiteur.getSolde() > somme) {
                compteCourantDebiteur.setSolde(compteCourantDebiteur.getSolde() - somme);
                compteEpargneCrediteur.setSolde(compteEpargneCrediteur.getSolde() + somme);
                compteCourantRepository.saveAndFlush(compteCourantDebiteur);
                compteEpargneRepository.saveAndFlush(compteEpargneCrediteur);
            } else {
                throw new RuntimeException("Le solde du compte débiteur est insuffisant");
            }
        }
        else {
            throw new RuntimeException("Identifiants non trouvés");
        }


    }

    @Override
    @Transactional

    public void virementCompteCourantToCourant(Integer idCompteCourantDebiteur, Integer idCompteCourantCrediteur, Double somme) {
        if(compteCourantRepository.existsById(idCompteCourantDebiteur) && compteCourantRepository.existsById(idCompteCourantCrediteur)) {
            CompteCourant compteCourantDebiteur = compteCourantRepository.findById(idCompteCourantDebiteur).get();
            CompteCourant compteCourantCrediteur = compteCourantRepository.findById(idCompteCourantCrediteur).get();
            if (compteCourantDebiteur.getSolde() > somme) {
                compteCourantDebiteur.setSolde(compteCourantDebiteur.getSolde() - somme);
                compteCourantCrediteur.setSolde(compteCourantCrediteur.getSolde() + somme);
                compteCourantRepository.saveAndFlush(compteCourantDebiteur);
                compteCourantRepository.saveAndFlush(compteCourantCrediteur);
            } else {
                throw new RuntimeException("Le solde du compte débiteur est insuffisant");
            }
        }
        else {
            throw new RuntimeException("Identifiants non trouvés");
        }


    }

    @Override
    @Transactional
    public void virementCompteEpargneToCompteCourant(Integer idCompteEpargne, Integer idCompteCourant, Double somme) {
        if (compteEpargneRepository.existsById(idCompteEpargne) && compteCourantRepository.existsById(idCompteCourant)){
            CompteEpargne compteEpargneDebiteur = compteEpargneRepository.findById(idCompteEpargne).get();
            CompteCourant compteCourantCrediteur = compteCourantRepository.findById(idCompteCourant).get();

            if(compteEpargneDebiteur.getSolde() > somme){
                compteEpargneDebiteur.setSolde(compteEpargneDebiteur.getSolde() - somme);
                compteCourantCrediteur.setSolde(compteCourantCrediteur.getSolde() + somme);
                compteCourantRepository.saveAndFlush(compteCourantCrediteur);
                compteEpargneRepository.saveAndFlush(compteEpargneDebiteur);
            }
            else {
                throw new RuntimeException("Le solde du compte débiteur est insuffisant");
            }
        }
        else {
            throw new RuntimeException("Identifiants non retrouvés");
        }
    }

    @Override
    @Transactional
    public void virementCompteEpargneToCompteEpargne(Integer idCompteEpargneDebiteur, Integer idCompteEpargneCrediteur, Double somme) {
        if (compteEpargneRepository.existsById(idCompteEpargneDebiteur) && compteEpargneRepository.existsById(idCompteEpargneCrediteur)){
            CompteEpargne compteEpargneDebiteur = compteEpargneRepository.findById(idCompteEpargneDebiteur).get();
            CompteEpargne compteEpargneCrediteur = compteEpargneRepository.findById(idCompteEpargneCrediteur).get();

            if(compteEpargneDebiteur.getSolde() > somme){
                compteEpargneDebiteur.setSolde(compteEpargneDebiteur.getSolde() - somme);
                compteEpargneCrediteur.setSolde(compteEpargneCrediteur.getSolde() + somme);
                compteEpargneRepository.saveAndFlush(compteEpargneCrediteur);
                compteEpargneRepository.saveAndFlush(compteEpargneDebiteur);
            }
            else {
                throw new RuntimeException("Le solde du compte débiteur est insuffisant");
            }
        }
        else {
            throw new RuntimeException("Identifiants non retrouvés");
        }
    }
}



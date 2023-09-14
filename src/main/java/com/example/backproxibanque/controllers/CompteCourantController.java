package com.example.backproxibanque.controllers;

import com.example.backproxibanque.dtos.ClientDto;
import com.example.backproxibanque.dtos.CompteBancaireDto;
import com.example.backproxibanque.entities.CompteCourant;
import com.example.backproxibanque.entities.CompteEpargne;
import com.example.backproxibanque.services.CompteBancaireServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("compteCourant")
public class CompteCourantController {
    @Autowired
    private CompteBancaireServices compteBancaireServices;

    @GetMapping
    public ResponseEntity<List<CompteBancaireDto>> getAllCompteBancaire(){
        try{
            return new ResponseEntity<>(compteBancaireServices.getAllCompteBancaire(), HttpStatus.OK);
        } catch (RuntimeException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<CompteBancaireDto> getCompteCourantById(@PathVariable Integer id){
        try{
            return new ResponseEntity<>(compteBancaireServices.getByIdCompteCourant(id), HttpStatus.OK);
        } catch (RuntimeException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompteCourant(@PathVariable Integer id){
        try{
            compteBancaireServices.deleteCompteCourantById(id);
            return new ResponseEntity<>("Le compte courant : "+id +" a été supprimé avec succès",HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }



    @PutMapping("/{id}")
    public ResponseEntity<CompteCourant> updateCompteCourant(@PathVariable Integer id, @RequestBody CompteBancaireDto compteBancaireDto){
        try{
            return new ResponseEntity<>(compteBancaireServices.updateCompteCourant(id, compteBancaireDto), HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
        public ResponseEntity<String> virementCompteCourantToCompteEpargne(
                @RequestParam Integer idCompteCourant,
                @RequestParam Integer idCompteEpargne,
                @RequestParam Double somme) {

            try {
                compteBancaireServices.virementCompteCourantToCompteEpargne(idCompteCourant, idCompteEpargne, somme);
                return ResponseEntity.ok("Virement réussi.");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Erreur lors du virement : " + e.getMessage());
            }
        }

    @RestController
    @RequestMapping("/comptes")
    public class VirementController {

        private final CompteBancaireServices compteBancaireServices;

        @Autowired
        public VirementController(CompteBancaireServices compteBancaireServices) {
            this.compteBancaireServices = compteBancaireServices;
        }

//
    }





}

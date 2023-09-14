package com.example.backproxibanque.controllers;

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
@RequestMapping("compteEpargne")
public class CompteEpargneController {
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
    public ResponseEntity<CompteBancaireDto> getCompteEpargneById(@PathVariable Integer id){
        try{
            return new ResponseEntity<>(compteBancaireServices.getByIdCompteEpargne(id), HttpStatus.OK);
        } catch (RuntimeException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompteEpargne(@PathVariable Integer id){
        try{
            compteBancaireServices.deleteCompteEpargneById(id);
            return new ResponseEntity<>("Le compte epargne : "+id +" a été supprimé avec succès",HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompteEpargne> updateCompteEpargne(@PathVariable Integer id, @RequestBody CompteBancaireDto compteBancaireDto){
        try{
            return new ResponseEntity<>(compteBancaireServices.updateCompteEpargne(id, compteBancaireDto), HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

package com.example.backproxibanque.controllers;

import com.example.backproxibanque.dtos.ClientDto;
import com.example.backproxibanque.dtos.CompteBancaireDto;
import com.example.backproxibanque.services.ClientServices;
import com.example.backproxibanque.services.CompteBancaireServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("compteBancaire")
public class CompteBancaireController {
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
}

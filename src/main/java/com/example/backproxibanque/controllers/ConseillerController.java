package com.example.backproxibanque.controllers;

import com.example.backproxibanque.dtos.ConseillerDto;
import com.example.backproxibanque.entities.Client;
import com.example.backproxibanque.services.ConseillerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("conseiller")
public class ConseillerController {

    @Autowired
    private ConseillerServices conseillerServices;

    @GetMapping("login")
    public ResponseEntity<Integer> login(@RequestParam("lastname") String lastname, @RequestParam("firstname") String firstname){
        try {
            return new ResponseEntity<>(conseillerServices.loginConseiller(lastname, firstname), HttpStatus.OK);
        } catch (RuntimeException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("getinfo")
    public List<Client> getInfoForLogeed(@RequestParam("lastname") String lastname, @RequestParam("firstname") String firstname){
        return conseillerServices.getInfoForLogged(lastname, firstname);
    }
}

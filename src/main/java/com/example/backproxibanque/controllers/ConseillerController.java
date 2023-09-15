package com.example.backproxibanque.controllers;

import com.example.backproxibanque.dtos.ConseillerDto;
import com.example.backproxibanque.entities.Client;
import com.example.backproxibanque.models.ConseillerIdendification;
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

    @PostMapping("login")
    public ResponseEntity<ConseillerDto> login(@RequestBody() ConseillerIdendification conseillerIdendification){
        try {
            return new ResponseEntity<>(conseillerServices.login(conseillerIdendification), HttpStatus.OK);
        } catch (RuntimeException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

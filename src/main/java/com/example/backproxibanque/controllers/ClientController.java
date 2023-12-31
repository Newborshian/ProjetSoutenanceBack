package com.example.backproxibanque.controllers;

import com.example.backproxibanque.dtos.ClientDto;
import com.example.backproxibanque.entities.Client;
import com.example.backproxibanque.repositories.ClientRepository;
import com.example.backproxibanque.services.ClientServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("client")
public class ClientController {
    @Autowired
    private ClientServices clientServices;

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping
    public ResponseEntity<List<ClientDto>> getAllClient(@RequestParam("id") Integer id){
        try{
            return new ResponseEntity<>(clientServices.getAllClients(id), HttpStatus.OK);
        } catch (RuntimeException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> getClientById(@PathVariable Integer id){
        try{
            return new ResponseEntity<>(clientServices.getClientById(id), HttpStatus.OK);
        } catch (RuntimeException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<Client> saveClient(@RequestBody ClientDto clientDto){
        try{
            return new ResponseEntity<>(clientServices.saveClient(clientDto), HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteClient(@PathVariable Integer id){
        Map<String, String> response = new HashMap<>();

        try {
            System.out.println("Je suis bien dans la méthode delete client");
            clientServices.deleteClientById(id);
            response.put("message", "Le client a été supprimé avec succès");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Integer id, @RequestBody ClientDto clientDto){
        try{
            return new ResponseEntity<>(clientServices.updateClient(id, clientDto), HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("name/{name}")
    public ResponseEntity<List<ClientDto>> getClientByName(@PathVariable String name){
        return new ResponseEntity<>(clientServices.getClientByName(name), HttpStatus.OK);
    }
}

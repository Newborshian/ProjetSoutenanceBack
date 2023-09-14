package com.example.backproxibanque.services;

import com.example.backproxibanque.dtos.ConseillerDto;
import com.example.backproxibanque.entities.Client;
import com.example.backproxibanque.entities.Conseiller;

import java.util.List;

public interface ConseillerServices {

    Integer loginConseiller(String lastname, String firstname);

    List<Client> getInfoForLogged(String lastname, String password);

    ConseillerDto toDto(Conseiller conseiller);

}

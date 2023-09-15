package com.example.backproxibanque.services;

import com.example.backproxibanque.dtos.ConseillerDto;
import com.example.backproxibanque.entities.Client;
import com.example.backproxibanque.entities.Conseiller;
import com.example.backproxibanque.models.ConseillerIdendification;

import java.util.List;

public interface ConseillerServices {

    ConseillerDto login(ConseillerIdendification conseillerIdendification);

    ConseillerDto toDto(Conseiller conseiller);

}

package com.example.backproxibanque.services.imp;

import com.example.backproxibanque.dtos.ConseillerDto;
import com.example.backproxibanque.entities.Client;
import com.example.backproxibanque.entities.Conseiller;
import com.example.backproxibanque.models.ConseillerIdendification;
import com.example.backproxibanque.repositories.ClientRepository;
import com.example.backproxibanque.repositories.ConseillerRepository;
import com.example.backproxibanque.services.ClientServices;
import com.example.backproxibanque.services.ConseillerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConseillerServicesImp implements ConseillerServices {

    @Autowired
    private ConseillerRepository conseillerRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientServices clientServices;

    @Override
    public ConseillerDto toDto(Conseiller conseiller) {
        ConseillerDto conseillerDto = new ConseillerDto();
        conseillerDto.setId(conseiller.getId());
        conseillerDto.setLastname(conseiller.getLastname());
        conseillerDto.setFirstName(conseiller.getFirstName());
        conseillerDto.setClientList(clientServices.createList(clientRepository.findByConseiller(conseiller)));
        return conseillerDto;
    }

    @Override
    public ConseillerDto login(ConseillerIdendification conseillerIdendification) {
        if (conseillerRepository.existsByMail(conseillerIdendification.getMail())){
            ConseillerDto conseillerDto = toDto(conseillerRepository.findByMailAndPassword(conseillerIdendification.getMail(), conseillerIdendification.getPassword()));
           return conseillerDto;
        } else throw new RuntimeException("Conseillé non trouvé");
    }
}

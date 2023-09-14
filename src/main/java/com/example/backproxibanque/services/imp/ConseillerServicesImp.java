package com.example.backproxibanque.services.imp;

import com.example.backproxibanque.dtos.ConseillerDto;
import com.example.backproxibanque.entities.Client;
import com.example.backproxibanque.entities.Conseiller;
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

    @Override
    public ConseillerDto toDto(Conseiller conseiller) {
        ConseillerDto conseillerDto = new ConseillerDto();
        conseillerDto.setId(conseiller.getId());
        conseillerDto.setLastname(conseiller.getLastname());
        conseillerDto.setFirstName(conseiller.getFirstName());
        conseillerDto.setClientList(clientRepository.findByConseiller(conseiller));
        return conseillerDto;
    }

    @Override
    public Integer loginConseiller(String lastname, String firstname) {
        if (conseillerRepository.existsByLastnameAndFirstName(lastname, firstname)){
            return 1;
        } else throw new RuntimeException("Conseillé non trouvé");
    }

    @Override
    public List<Client> getInfoForLogged(String lastname, String firstname) {
        Conseiller conseiller = conseillerRepository.findByLastnameAndFirstName(lastname, firstname);
        ConseillerDto conseillerDto = this.toDto(conseiller);
        return conseillerDto.getClientList();
    }


}

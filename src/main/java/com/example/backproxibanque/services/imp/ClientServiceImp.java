package com.example.backproxibanque.services.imp;

import com.example.backproxibanque.dtos.ClientDto;
import com.example.backproxibanque.entities.Client;
import com.example.backproxibanque.repositories.ClientRepository;
import com.example.backproxibanque.services.ClientServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientServiceImp implements ClientServices {

    @Autowired
    private ClientRepository clientRepository;



    @Override
    public List<ClientDto> getAllClients() {
        List<Client> clientList = clientRepository.findAll();
        List<ClientDto> clientDtoList = new ArrayList<>();
        if (!clientList.isEmpty()){
           for (Client client : clientList){
               clientDtoList.add(toDto(client));
           }
           return clientDtoList;
        } else throw new RuntimeException("Oh Lalala");

    }

    @Override
    public ClientDto toDto(Client client) {
        ClientDto clientDto = new ClientDto();
        clientDto.setId(client.getId());
        clientDto.setLastname(client.getLastname());
        clientDto.setFirstname(client.getFirstname());
        clientDto.setAddress(client.getAddress());
        clientDto.setCity(client.getCity());
        clientDto.setZipcode(client.getZipcode());
        clientDto.setPhoneNumber(client.getPhonenumber());
        clientDto.setIdConseiller(client.getConseiller().getId());
        return clientDto;
    }
}

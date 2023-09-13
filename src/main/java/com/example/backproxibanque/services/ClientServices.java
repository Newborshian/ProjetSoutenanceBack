package com.example.backproxibanque.services;

import com.example.backproxibanque.dtos.ClientDto;
import com.example.backproxibanque.entities.Client;

import java.util.List;

public interface ClientServices {
    List<ClientDto> getAllClients();

    ClientDto toDto(Client client);

    Client saveClient(ClientDto clientDto);

    void  deleteClientById(Integer id );

    Client updateClient(Integer id, ClientDto clientDto);

    ClientDto getClientById(Integer id);



}

package com.example.backproxibanque.services;

import com.example.backproxibanque.dtos.ClientDto;
import com.example.backproxibanque.dtos.CompteBancaireDto;
import com.example.backproxibanque.entities.Client;

import java.util.List;

public interface ClientServices {
List<ClientDto> getAllClients(Integer id);
ClientDto toDto(Client client);

Client saveClient(ClientDto clientDto);

void  deleteClientById(Integer id);

Client updateClient(Integer id, ClientDto clientDto);

ClientDto getClientById(Integer id);

List<ClientDto> createList(List<Client> clientList);

List<ClientDto> getClientByName(String name);
}

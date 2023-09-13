package com.example.backproxibanque.services.imp;

import com.example.backproxibanque.dtos.ClientDto;
import com.example.backproxibanque.entities.Client;
import com.example.backproxibanque.repositories.ClientRepository;
import com.example.backproxibanque.repositories.ConseillerRepository;
import com.example.backproxibanque.services.ClientServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ClientServiceImp implements ClientServices {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ConseillerRepository conseillerRepository;
    @Override
    public List<ClientDto> getAllClients() {
        List<ClientDto> clientDtoList = new ArrayList<>();
        List<Client> clientList = clientRepository.findAll();

        if (!clientList.isEmpty()){
        for(Client  client : clientList ){
           clientDtoList.add(toDto(client)) ;
        }
        return clientDtoList;
        }
        else throw new RuntimeException();
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
        clientDto.setPhonenumber(client.getPhonenumber());
        clientDto.setIdConseiller(client.getConseiller().getId());
        return clientDto;
    }

    @Override
    public Client saveClient(ClientDto clientDto) {
        Client newclient = new Client();
        newclient.setLastname(clientDto.getLastname());
        newclient.setFirstname(clientDto.getFirstname());
        newclient.setAddress(clientDto.getAddress());
        newclient.setCity(clientDto.getCity());
        newclient.setZipcode(clientDto.getZipcode());
        newclient.setPhonenumber(clientDto.getPhonenumber());
        if (!conseillerRepository.findById(clientDto.getIdConseiller()).isEmpty()){
        newclient.setConseiller(conseillerRepository.findById(clientDto.getIdConseiller()).get());
        return clientRepository.saveAndFlush(newclient);}
    else{
        throw new RuntimeException("Veuillez affilier un conseiller à ce client");
    }
}

    @Override
    public void deleteClientById(Integer id) {
        if (clientRepository.findById(id).isPresent()){
        clientRepository.deleteById(id);}
        else{
            throw new RuntimeException("l'identifiant du client n'a pas été trouvé");
        }
    }

    @Override
    public Client updateClient(Integer id, ClientDto clientDto) {
        if (clientRepository.existsById(id)){
        Client newclient = clientRepository.findById(id).get();
        newclient.setLastname(clientDto.getLastname());
        newclient.setFirstname(clientDto.getFirstname());
        newclient.setAddress(clientDto.getAddress());
        newclient.setCity(clientDto.getCity());
        newclient.setZipcode(clientDto.getZipcode());
        newclient.setPhonenumber(clientDto.getPhonenumber());
        if (conseillerRepository.existsById(clientDto.getIdConseiller())){
            newclient.setConseiller(conseillerRepository.findById(clientDto.getIdConseiller()).get());
            return clientRepository.saveAndFlush(newclient);}
        else{
            throw new RuntimeException("Veuillez affilier un conseiller à ce client");
        }}
        else {
            throw new RuntimeException("l'identifiant du client n'a pas été trouvé");
        }
    }

    @Override
    public ClientDto getClientById(Integer id) {
        Client client = clientRepository.findById(id).get();
        if (clientRepository.findById(id).isPresent()) {
            return toDto(client);
        }  else {
            throw new RuntimeException("l'identifiant du client n'a pas été trouvé");
        }
    }
}

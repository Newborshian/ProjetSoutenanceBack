package com.example.backproxibanque.services.imp;

import com.example.backproxibanque.dtos.ClientDto;
import com.example.backproxibanque.entities.Client;
import com.example.backproxibanque.entities.Conseiller;
import com.example.backproxibanque.repositories.ClientRepository;
import com.example.backproxibanque.services.ClientServices;

import org.junit.jupiter.api.Test;

import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
class ClientServiceImpTest {

    @Autowired
    private ClientServices clientServices;

    @Mock
    private ClientRepository clientRepository;

    Conseiller conseiller = new Conseiller(1, "Dupont", "Jean");

    @Test
    void getAllClients() {
        List<ClientDto> clientDtoList = new ArrayList<>();
        clientDtoList.add(new ClientDto(2, "Smith", "Jane", "456 Elm St", "Los Angeles", 90001, "555-5678", 2));
        assertThat(this.clientServices.getAllClients().get(0).toString()).isEqualTo(clientDtoList.get(0).toString());
    }

    @Test
    void toDto() {
        Client client = new Client(1, "Doe", "John", "123 Main St", "Chicago", 10001, "555-1234", conseiller);
        ClientDto clientDto = new ClientDto(1, "Doe", "John", "123 Main St", "Chicago", 10001, "555-1234", 1);
        assertThat(this.clientServices.toDto(client).toString()).isEqualTo(clientDto.toString());
    }
}

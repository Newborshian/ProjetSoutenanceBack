package com.example.backproxibanque.dtos;

import com.example.backproxibanque.entities.Client;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ConseillerDto {

    private Integer id;
    private String lastname;
    private String firstName;
    private List<Client> clientList;
}

package com.example.backproxibanque.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ClientDto {
    private Integer id;
    private String lastname;
    private String firstname;
    private String address;
    private String city;
    private Integer zipcode;
    private String phoneNumber;
    private Integer idConseiller;
}

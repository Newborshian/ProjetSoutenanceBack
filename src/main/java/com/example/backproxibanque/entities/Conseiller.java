package com.example.backproxibanque.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "conseiller")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Conseiller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "firstname")
    private String firstName;
    @Column(name = "mail")
    private String mail;
    @Column(name = "password")
    private String password;
}

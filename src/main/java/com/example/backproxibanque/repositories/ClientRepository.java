package com.example.backproxibanque.repositories;

import com.example.backproxibanque.entities.Client;
import com.example.backproxibanque.entities.Conseiller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    List<Client> findByConseiller(Conseiller conseiller);

}

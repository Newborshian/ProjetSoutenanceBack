package com.example.backproxibanque.repositories;

import com.example.backproxibanque.dtos.CompteBancaireDto;
import com.example.backproxibanque.entities.CompteCourant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface CompteCourantRepository extends JpaRepository<CompteCourant, Integer> {

    List<CompteCourant> findByClient_Id(Integer idClient);
}

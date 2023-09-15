package com.example.backproxibanque.repositories;

import com.example.backproxibanque.entities.CompteCourant;
import com.example.backproxibanque.entities.CompteEpargne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface CompteEpargneRepository extends JpaRepository<CompteEpargne, Integer> {

    List<CompteEpargne> findByClient_Id(Integer idClient);
}

package com.example.backproxibanque.repositories;

import com.example.backproxibanque.entities.CompteEpargne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface CompteEpargneRepository extends JpaRepository<CompteEpargne, Integer> {
}

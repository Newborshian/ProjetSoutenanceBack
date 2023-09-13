package com.example.backproxibanque.repositories;

import com.example.backproxibanque.entities.CompteEpargne;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompteEpargneRepository extends JpaRepository<CompteEpargne, Integer> {
}

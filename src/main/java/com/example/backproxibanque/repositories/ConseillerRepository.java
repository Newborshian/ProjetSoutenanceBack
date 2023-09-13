package com.example.backproxibanque.repositories;

import com.example.backproxibanque.entities.Conseiller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConseillerRepository extends JpaRepository<Conseiller, Integer> {
}

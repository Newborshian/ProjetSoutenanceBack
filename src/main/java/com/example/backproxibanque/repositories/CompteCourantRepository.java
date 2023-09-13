package com.example.backproxibanque.repositories;

import com.example.backproxibanque.entities.CompteCourant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompteCourantRepository extends JpaRepository<CompteCourant, Integer> {
}

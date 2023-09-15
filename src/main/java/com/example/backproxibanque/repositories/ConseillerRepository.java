package com.example.backproxibanque.repositories;

import com.example.backproxibanque.entities.Conseiller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

public interface ConseillerRepository extends JpaRepository<Conseiller, Integer> {


    Boolean existsByMail(@Param("mail") String mail);
    Conseiller findByMailAndPassword(@Param("mail") String mail, @Param("password") String password);
}

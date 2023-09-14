package com.example.backproxibanque.repositories;

import com.example.backproxibanque.entities.Conseiller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ConseillerRepository extends JpaRepository<Conseiller, Integer> {

    Boolean existsByLastnameAndFirstName(@Param("firstname") String firstname, @Param("lastname") String lastname );
    Conseiller findByLastnameAndFirstName(@Param("firstname") String firstname, @Param("lastname") String lastname);
}

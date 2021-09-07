package com.mentor4you.repository;

import com.mentor4you.model.Microdistricts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MicrodistrictsRepository extends JpaRepository<Microdistricts, Integer> {
    Optional<Microdistricts> findById(int id);

//    @Query("Select m from Microdictricts m WHERE m.name=?1")
//    List<Microdistricts> findByName(String Name);

}
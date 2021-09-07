package com.mentor4you.repository;
import com.mentor4you.model.Countries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CountriesRepository extends JpaRepository<Countries, Integer> {
    Optional<Countries> findById(int id);

    @Query("Select a from Countries a WHERE a.name=?1")
    List<Countries> findByName(String Name);
}
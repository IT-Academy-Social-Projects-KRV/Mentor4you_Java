package com.mentor4you.repository;
import com.mentor4you.model.Cities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CitiesRepository extends JpaRepository<Cities, Integer> {
    Optional<Cities> findById(int id);


    Optional<Cities> findByName(String name);

}
package com.mentor4you.repository;

import com.mentor4you.model.Categories;
import com.mentor4you.model.Mentees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories,Integer> {
    Optional<Categories> findById(int id);
}

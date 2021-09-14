package com.mentor4you.repository;

import com.mentor4you.model.Categories;
import com.mentor4you.model.Mentees;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRepository extends JpaRepository<Categories,Integer> {
}

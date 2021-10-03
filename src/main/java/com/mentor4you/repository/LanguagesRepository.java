package com.mentor4you.repository;

import com.mentor4you.model.Languages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguagesRepository extends JpaRepository<Languages, Integer> {

    Languages findByName(String name);
}
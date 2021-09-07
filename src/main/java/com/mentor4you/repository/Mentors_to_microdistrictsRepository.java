package com.mentor4you.repository;
import com.mentor4you.model.Mentors_to_microdistricts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface Mentors_to_microdistrictsRepository extends JpaRepository<Mentors_to_microdistricts, Integer> {


    Optional<Mentors_to_microdistricts> findById(int id);
//
//    @Query("Select mm from Mentors_to_microdistricts a WHERE mm.mentor_id =?1")
//    Mentors_to_microdistricts findByMentorId(int id);
}
package com.mentor4you.repository;

import com.mentor4you.model.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface MentorRepository
        extends JpaRepository<Mentor, Integer> {
    Optional<Mentor> findById(int id);
}

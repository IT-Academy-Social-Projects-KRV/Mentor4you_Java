package com.mentor4you.repository;

import com.mentor4you.model.Accounts;
import com.mentor4you.model.ContactsToAccounts;
import com.mentor4you.model.Mentors;
import com.mentor4you.model.TypeContacts;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Repository
@Transactional(readOnly = true)
public interface MentorRepository extends JpaRepository<Mentors, Integer> {

    Optional<Mentors> findById(int id);

    @Query("Select distinct m.mentors from Mentors_to_categories m WHERE m.categories.name=?1")
    List<Mentors> findMentorsByCategoryName(String name);

    @Query(value = "Select distinct m from Mentors m ORDER BY m.rating DESC")
    List<Mentors> findMentorsBestRating(Pageable pageable);

}

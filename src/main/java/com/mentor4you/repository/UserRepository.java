package com.mentor4you.repository;

import com.mentor4you.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findById(int id);

    @Query("SELECT u FROM User u WHERE u.role_id.name = ?1")
    List<User> findByRoleName(String mentor);

    @Query("SELECT u FROM User u WHERE u.id=?1 and u.role_id.name='Mentor'")
    Optional<User> findById(Integer integer);
}

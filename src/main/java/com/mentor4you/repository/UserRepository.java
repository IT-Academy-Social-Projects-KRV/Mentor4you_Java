package com.mentor4you.repository;

import com.mentor4you.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findById(int id);


    Optional<User> findByEmail(String email);

    User findUserByEmail(String email);

}

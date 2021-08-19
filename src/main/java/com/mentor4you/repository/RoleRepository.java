package com.mentor4you.repository;

import com.mentor4you.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface RoleRepository extends JpaRepository<Roles, Integer> {
    Optional<Roles> findById(int id);
}

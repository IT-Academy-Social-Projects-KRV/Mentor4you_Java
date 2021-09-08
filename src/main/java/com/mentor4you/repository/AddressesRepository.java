package com.mentor4you.repository;

import com.mentor4you.model.Addresses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface AddressesRepository extends JpaRepository<Addresses, Integer> {
    Optional<Addresses> findById(int id);


}
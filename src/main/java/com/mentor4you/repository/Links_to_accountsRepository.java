package com.mentor4you.repository;

import com.mentor4you.model.Links_to_accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface Links_to_accountsRepository
        extends JpaRepository<Links_to_accounts, Integer> {

    Optional<Links_to_accounts> findById(int id);

    @Query("Select sn from Links_to_accounts sn WHERE sn.url =?1")
    Links_to_accounts findByUrl(String url);
}

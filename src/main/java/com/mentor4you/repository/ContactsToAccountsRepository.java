package com.mentor4you.repository;

import com.mentor4you.model.ContactsToAccounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ContactsToAccountsRepository
        extends JpaRepository<ContactsToAccounts, Integer> {

    Optional<ContactsToAccounts> findById(int id);

    @Query("Select sn from ContactsToAccounts sn WHERE sn.contactData =?1")
    ContactsToAccounts findByUrl(String url);
}

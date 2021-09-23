package com.mentor4you.service;

import com.mentor4you.model.Accounts;
import com.mentor4you.model.ContactsToAccounts;
import com.mentor4you.model.TypeContacts;
import com.mentor4you.repository.AccountRepository;
import com.mentor4you.repository.ContactsToAccountsRepository;
import com.mentor4you.repository.TypeContactsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactsToAccountsService {
    @Autowired
    ContactsToAccountsRepository contactsToAccountsRepository;
    TypeContactsRepository typeContactsRepository;
    AccountRepository accountRepository;

    public ContactsToAccountsService(ContactsToAccountsRepository contactsToAccountsRepository,
                                     TypeContactsRepository typeContactsRepository,
                                     AccountRepository accountRepository) {
        this.contactsToAccountsRepository = contactsToAccountsRepository;
        this.typeContactsRepository = typeContactsRepository;
        this.accountRepository = accountRepository;
    }

    //Create new row in ContactsToAccount table
    public String createNewContactData(int accountId, String typeCont, String contDate){
        ContactsToAccounts contactsToAccounts = new ContactsToAccounts();
        contactsToAccounts.setAccounts(accountRepository.getById(accountId));
        TypeContacts typeContacts = typeContactsRepository.findByName(typeCont);
        if (typeContacts!= null){
            contactsToAccounts.setTypeContacts(typeContacts);
            contactsToAccounts.setContactData(contDate);
            contactsToAccountsRepository.save(contactsToAccounts);
            return "New raw was created";
        }
        else{
            return typeCont+ "not found";
        }
    }




}

package com.mentor4you.service;

import com.mentor4you.exception.MenteeNotFoundException;
import com.mentor4you.exception.MentorNotFoundException;
import com.mentor4you.model.*;
import com.mentor4you.model.DTO.MenteeResponseDTO;
import com.mentor4you.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MenteeService {
    @Autowired
    AccountRepository accountRepository;
    MenteeRepository menteeRepository;
    UserRepository userRepository;
    ContactsToAccountsRepository contactsToAccountsRepository;

    public MenteeService(AccountRepository accountRepository, MenteeRepository menteeRepository) {
        this.accountRepository = accountRepository;
        this.menteeRepository = menteeRepository;
    }

    public List<Mentees> getFullInfoAllMentees(){
        int theMentees = accountRepository.findByRole(Role.MENTEE).size();
        if(theMentees!=0){
            return menteeRepository.findAll();
        }
        throw new MentorNotFoundException("Mentees not found");

    }

    public Optional<Mentees> getMenteeById(int id){
        Optional<Mentees> theMentee = menteeRepository.findById(id).stream().filter(e->e.getId()==id).findFirst();
        if(theMentee.isPresent()) {
            return theMentee;
        }
        throw new MenteeNotFoundException("Mentee with id = "+ id +" not found");
    }
    public MenteeResponseDTO getMenteeByEmail(User user){
       // User user = userRepository.getById(id);

       // if(user==null)return new ResponseEntity<MenteeResponseDTO>(new MenteeResponseDTO(), HttpStatus.OK);


            Map<String, String> socialMap = new HashMap<>();

            //Social_networks socialNetworks = socialNetworksRepository.getById(id);



                List<ContactsToAccounts> listConToAkk = contactsToAccountsRepository. findAllByAccounts(user.getId());
                listConToAkk.add(new ContactsToAccounts());

                if (listConToAkk.size() > 0) {
                    for (ContactsToAccounts lA : listConToAkk) {
                        String typContact = lA.getTypeContacts().getName();
                        String contData = lA.getContactData();
                        socialMap.put(typContact, contData);
                    }
                } else {
                    socialMap.put("11", "11");
                }
                socialMap.put("234w", "asd");
                MenteeResponseDTO mDTO = new MenteeResponseDTO();
                mDTO.setFirstName(user.getFirst_name());
                mDTO.setLastName(user.getLast_name());
                mDTO.setEmail(user.getEmail());
                mDTO.setSocialMap(socialMap);
                return  mDTO;

    }
}

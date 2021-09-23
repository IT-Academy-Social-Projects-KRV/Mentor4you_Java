package com.mentor4you.service;

import com.mentor4you.exception.MenteeNotFoundException;
import com.mentor4you.exception.MentorNotFoundException;
import com.mentor4you.model.*;
import com.mentor4you.model.DTO.MenteeResponseDTO;
import com.mentor4you.model.DTO.MenteeUpdateRequest;
import com.mentor4you.repository.*;
import com.mentor4you.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class MenteeService {
    @Autowired
    AccountRepository accountRepository;
    MenteeRepository menteeRepository;
    JwtProvider jwtProvider;
    UserRepository userRepository;
    ContactsToAccountsRepository contactsToAccountsRepository;
    EmailService emailService;
    TypeContactsService typeContactsService;
    ContactsToAccountsService contactsToAccountsService;

    public MenteeService(AccountRepository accountRepository,
                         MenteeRepository menteeRepository,
                         JwtProvider jwtProvider,
                         UserRepository userRepository,
                         ContactsToAccountsRepository contactsToAccountsRepository,
                         EmailService emailService,
                         TypeContactsService typeContactsService,
                         ContactsToAccountsService contactsToAccountsService) {
        this.accountRepository = accountRepository;
        this.menteeRepository = menteeRepository;
        this.jwtProvider = jwtProvider;
        this.userRepository = userRepository;
        this.contactsToAccountsRepository = contactsToAccountsRepository;
        this.emailService = emailService;
        this.typeContactsService = typeContactsService;
        this.contactsToAccountsService = contactsToAccountsService;
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

    public ResponseEntity<MenteeResponseDTO> getOneMenteeByToken(HttpServletRequest req){
        String token = jwtProvider.getTokenFromRequest(req);
        String emailMy = jwtProvider.getLoginFromToken(token);
        User user = userRepository.findUserByEmail(emailMy);
        int id = user.getId();
        if (user.getRole().name() == Role.MENTEE.name()) {

            Map<String, String> socialMap = new HashMap<>();

            //Social_networks socialNetworks = socialNetworksRepository.getById(id);
            if (user != null) {

                List<ContactsToAccounts> listConToAkk = contactsToAccountsRepository.findAllByAccounts(id);

                if (listConToAkk.size() > 0) {
                    for (ContactsToAccounts lA : listConToAkk) {
                        String typContact = lA.getTypeContacts().getName();
                        String contData = lA.getContactData();
                        socialMap.put(typContact, contData);
                    }
                } else {
                    socialMap.put("", "");
                }
                MenteeResponseDTO mDTO = new MenteeResponseDTO();
                if(user.getFirst_name()==null){
                    mDTO.setFirstName("");}
                else{mDTO.setFirstName(user.getFirst_name());}
                if(user.getLast_name()==null)
                {mDTO.setLastName("");}
                else{mDTO.setLastName(user.getLast_name());}
                mDTO.setEmail(user.getEmail());
                mDTO.setSocialMap(socialMap);
                return new ResponseEntity<MenteeResponseDTO>(mDTO, HttpStatus.OK);
            }
            return null;
        }
        throw new MenteeNotFoundException("Mentees with id = " + id + " not found");
    }



}

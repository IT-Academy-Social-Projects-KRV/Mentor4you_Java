package com.mentor4you.controller;

import com.mentor4you.exception.ErrorObject;
import com.mentor4you.exception.MenteeNotFoundException;
import com.mentor4you.exception.MentorNotFoundException;
import com.mentor4you.model.*;
import com.mentor4you.model.DTO.MenteeResponseDTO;
import com.mentor4you.model.DTO.MenteeUpdateRequest;
import com.mentor4you.repository.ContactsToAccountsRepository;
import com.mentor4you.repository.UserRepository;
import com.mentor4you.service.MenteeService;
import com.mentor4you.service.MentorService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;



@RestController
@RequestMapping("/api/mentees")
public class MenteesController {



        @Autowired
        MenteeService menteesService;
        UserRepository userRepository;
        ContactsToAccountsRepository contactsToAccountsRepository;

    public MenteesController(MenteeService menteesService, UserRepository userRepository, ContactsToAccountsRepository contactsToAccountsRepository) {
        this.menteesService = menteesService;
        this.userRepository = userRepository;
        this.contactsToAccountsRepository = contactsToAccountsRepository;
    }

    //select mentees by id
        @GetMapping("/{email}")
        ResponseEntity<MenteeResponseDTO> getMenteeById(@PathVariable(value = "email") String email)
        {
             User user = userRepository.findUserByEmail(email);
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
                mDTO.setFirstName(user.getFirst_name());
                mDTO.setLastName(user.getLast_name());
                mDTO.setSocialMap(socialMap);
                return new ResponseEntity<MenteeResponseDTO>(mDTO, HttpStatus.OK);
            }
            return null;
        }
        throw new MenteeNotFoundException("Mentees with id = " + id + " not found");
    }


        @Operation(summary = "info about mentees")
        @GetMapping
        List<Mentees> getAllMentee(){
                return menteesService.getFullInfoAllMentees();
        }

        @ExceptionHandler
        public ResponseEntity<ErrorObject> handleException(MenteeNotFoundException ex) {
                ErrorObject eObject = new ErrorObject();
                eObject.setStatus(HttpStatus.NOT_FOUND.value());
                eObject.setMessage(ex.getMessage());
                eObject.setTimestamp(System.currentTimeMillis());
                return new ResponseEntity<>(eObject, HttpStatus.NOT_FOUND);
        }
}

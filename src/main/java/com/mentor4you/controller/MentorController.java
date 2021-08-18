package com.mentor4you.controller;

import com.mentor4you.model.Account;
import com.mentor4you.model.GroupServices;
import com.mentor4you.model.Mentor;
import com.mentor4you.model.PlaceOfMentorshipId;
import com.mentor4you.repository.AccountRepository;
import com.mentor4you.repository.GroupServicesRepository;
import com.mentor4you.repository.MentorRepository;
import com.mentor4you.repository.PlaceOfMentorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("api/add")
public class MentorController {

    @Autowired
    private MentorRepository mentorRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PlaceOfMentorRepository placeOfMentorRepository;
    @Autowired
    private GroupServicesRepository groupServicesRepository;


    public MentorController(MentorRepository mentorRepository,
                            AccountRepository accountRepository,
                            PlaceOfMentorRepository placeOfMentorRepository,
                            GroupServicesRepository groupServicesRepository) {
        this.mentorRepository = mentorRepository;
        this.accountRepository = accountRepository;
        this.placeOfMentorRepository = placeOfMentorRepository;
        this.groupServicesRepository = groupServicesRepository;
    }

    @GetMapping("/addMentor")
    public String registerRoles() throws ParseException {
        Long ACCOUNT_ID = 1l;
        int PLASE_OF_MENTOR = 1;
        int GROP_SERVICES = 1;

        Account account = accountRepository.findById(ACCOUNT_ID).get();
        PlaceOfMentorshipId placeOfMentorshipId = placeOfMentorRepository.findById(PLASE_OF_MENTOR).get();
        GroupServices groupServices = groupServicesRepository.findById(GROP_SERVICES).get();

        mentorRepository.save(
                new Mentor(
                        account,
                        "description",
                        true,
                        placeOfMentorshipId,
                        groupServices
                )
        );
        return "Account aded";
    }
}

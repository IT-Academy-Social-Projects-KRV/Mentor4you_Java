package com.mentor4you.controller;

import com.mentor4you.model.*;
import com.mentor4you.repository.AccountRepository;
import com.mentor4you.repository.GroupServicesRepository;
import com.mentor4you.repository.MentorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;


@RestController
@RequestMapping("/system")
public class SystemController {

    @Autowired
    private final GroupServicesRepository groupServicesRepository;
    private final AccountRepository accountRepository;
    private final MentorRepository mentorRepository;

    public SystemController(GroupServicesRepository groupServicesRepository,AccountRepository accountRepository, MentorRepository mentorRepository) {
        this.groupServicesRepository = groupServicesRepository;
        this.accountRepository = accountRepository;
        this.mentorRepository = mentorRepository;
    }

    @GetMapping("/add")
    //TODO delete after tests

    public String registerRoles() {
        groupServicesRepository.save(new GroupServices("No"));
        groupServicesRepository.save(new GroupServices("Yes"));
        groupServicesRepository.save(new GroupServices("Mix"));

        int NUMBER_ADMINS = 1;
        int NUMBER_MODERATORS = 3;
        int NUMBER_USERS = 15;

        createAdmin(NUMBER_ADMINS);
        createModerators(NUMBER_MODERATORS);
        createMentors(NUMBER_USERS);
        return "tables added";
    }


    //Create admin
    private void createAdmin(int numberAdmins) {
        for (int i = 1; i <= numberAdmins; i++) {

            User n = new User();
            n.setEmail(i + "_admin@email");
            n.setPassword(i + "_Adminpassword");
            n.setFirst_name(i + "_AdminFN");
            n.setLast_name(i + "_AdminLN");
            n.setRegistration_date(LocalDateTime.now());
            n.setStatus(true);
            n.setRole(Role.ADMIN);

            Accounts a = new Accounts();
            a.setUser(n);
            a.setPhoneNumber( "(" + i + ")" + i + i + i + i + i + "");
            a.setLast_visit(LocalDateTime.now());

            accountRepository.save(a);
        }
    }

    //Create moderators
    private void createModerators(int numberModerators) {
        for (int i = 1; i <= numberModerators; i++) {

            User n = new User();
            n.setEmail(i + "_moderator@email");
            n.setPassword(i + "_moderatorpassword");
            n.setFirst_name(i + "_moderatorFN");
            n.setLast_name(i + "_moderatorLN");
            n.setRegistration_date(LocalDateTime.now());
            n.setStatus(true);
            n.setRole(Role.MODERATOR);

            Accounts a = new Accounts();
            a.setUser(n);
            a.setPhoneNumber( "(" + i + ")" + i + i + i + i + i + "");
            a.setLast_visit(LocalDateTime.now());

            accountRepository.save(a);

        }

    }

    private void createMentors(int numberOfMentor){
        for (int i = 1; i <= numberOfMentor; i++) {

            User n = new User();
            n.setEmail(i + "_mentor@email");
            n.setPassword(i + "_mentorpassword");
            n.setFirst_name(i + "_mentorFN");
            n.setLast_name(i + "_mentorLN");
            n.setRegistration_date(LocalDateTime.now());
            n.setStatus(true);
            n.setRole(Role.MENTOR);

            Accounts a = new Accounts();
            a.setUser(n);
            a.setPhoneNumber( "(" + i + ")" + i + i + i + i + i + "");
            a.setLast_visit(LocalDateTime.now());

            Mentors m = new Mentors();
            m.setAccounts(a);
            m.setDescription("description");
            m.setShowable_status(true);
            m.isIs_online(true);
            m.isIs_offline_in(true);
            m.isIs_offline_out(true);

            mentorRepository.save(m);
        }
    }
}

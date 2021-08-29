package com.mentor4you.controller;

import com.mentor4you.model.*;
import com.mentor4you.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;


@RestController
@RequestMapping("/system")
public class SystemController {

    @Autowired
    private RoleRepository roleRepository;
    private GroupServicesRepository groupServicesRepository;
    private UserRepository userRepository;
    private AccountRepository accountRepository;
    private MentorRepository mentorRepository;
    private LanguagesRepository languagesRepository;

    public SystemController(RoleRepository roleRepository,
                            GroupServicesRepository groupServicesRepository,
                            UserRepository userRepository,
                            AccountRepository accountRepository,
                            MentorRepository mentorRepository,
                            LanguagesRepository languagesRepository) {
        this.roleRepository = roleRepository;
        this.groupServicesRepository = groupServicesRepository;
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.mentorRepository = mentorRepository;
        this.languagesRepository = languagesRepository;
    }

    @GetMapping("/add")
    //TODO delete after tests

    public String registerRoles() {
        roleRepository.save(new Roles("admin"));
        roleRepository.save(new Roles("moderator"));
        roleRepository.save(new Roles("mentor"));
        roleRepository.save(new Roles("mentee"));

        groupServicesRepository.save(new GroupServices("No"));
        groupServicesRepository.save(new GroupServices("Yes"));
        groupServicesRepository.save(new GroupServices("Mix"));

        int NUMBER_ADMINS = 1;
        int NUMBER_MODERATORS = 3;
        int NUMBER_USERS = 15;
        int numberAllUsers = 1 + NUMBER_ADMINS + NUMBER_MODERATORS + NUMBER_USERS;

        createLanguages();
        createAdmin(NUMBER_ADMINS);
        creatModerators(NUMBER_MODERATORS);
        createUsers(NUMBER_USERS, 3);  // 3 -> Mentors Role
        createAccounts(numberAllUsers);
        writeMentorsInTable(numberAllUsers);
        return "tables added";
    }

    @GetMapping("/getMentorsAll")
    public List<User> getMentorsAll() {
        return userRepository.findAll();
    }


    //Create admin
    private void createAdmin(int numberAdmins) {
        int ROLES_ID = 1; // admin
        for (int i = 1; i <= numberAdmins; i++) {
            userRepository.save(
                    new User(
                            roleRepository.findById(ROLES_ID).get(),
                            i + "_admin@email",
                            i + "_Adminpassword",
                            i + "_AdminFN",
                            i + "_AdminLN",
                            "",
                            LocalDateTime.now(),
                            true
                    )
            );
        }
    }

    //Create moderators
    private void creatModerators(int numberModerators) {
        int ROLES_ID = 2; //moderator
        for (int i = 1; i <= numberModerators; i++) {
            userRepository.save(
                    new User(
                            roleRepository.findById(ROLES_ID).get(),
                            i + "_moderator@email",
                            i + "_moderatorpassword",
                            i + "_moderatorFN",
                            i + "_moderatorLN",
                            "",
                            LocalDateTime.now(),
                            true
                    )
            );
        }

    }

    //Create mentors
    private void createUsers(int numberUsers, int roles) {

        for (int i = 1; i <= numberUsers; i++) {
            userRepository.save(
                    new User(

                            roleRepository.findById(roles).get(),
                            i + "_mentor@email",
                            i + "_mentorpassword",
                            i + "_mentorFN",
                            i + "_mentorLN",
                            "",
                            LocalDateTime.now(),
                            true
                    )
            );
        }
    }
    //CreateLanguages
    private void createLanguages(){
        List<Languages> languagesList = new ArrayList<>();
        languagesList.add(new Languages("ukraine"));
        languagesList.add(new Languages("english"));
        languagesList.add(new Languages("russian"));
        languagesList.add(new Languages("polish"));
        languagesList.add(new Languages("сzech"));

        languagesList.forEach(ln -> languagesRepository.saveAndFlush(ln));
    }

    //CreateAccounts
    private void createAccounts(int numberAllUsers) {
        for (int i = 1; i < numberAllUsers; i++) {
            User user = userRepository.findById(i).get();
            accountRepository.save(
                    new Accounts(
                            user,
                            "(" + i + ")" + i + i + i + i + i + "",
                            LocalDateTime.now()
                    )
            );
        }
        System.out.println("accounts add");
    }

    //added languages into Account
    @GetMapping("/addLanguages")
    private String addLanguages(){
        Random random = new Random();
        Languages languages = languagesRepository.getById(1);

        List<Accounts> list = accountRepository.findAll();
        list.forEach(a -> {
                    a.addLanguages(languages);
                    a.addLanguages(languagesRepository.getById(random.nextInt(4)+2));
                    accountRepository.saveAndFlush(a);
                }
        );
        return "languages added";
    }


    //CreateMentors
    private void writeMentorsInTable(int numberAllusers) {
        System.out.println("inside WriteMentors");
        int GROUP_SERVICES = 1;
        int MENTORS_ROLE = 3;
        GroupServices groupService =
                groupServicesRepository.findById(GROUP_SERVICES).get();

        for (int i = 1; i < numberAllusers; i++) {
            Accounts account = accountRepository.findById(i).get();
            int rr = account.getUser().getRole_id().getId();

            //check to ROLE in account
            //if is it "Mentor" > creat
            if (MENTORS_ROLE == (account.getUser().getRole_id().getId())) {
                mentorRepository.save(
                        new Mentors(
                                account,
                                "description",
                                true,
                                groupService,
                                true,
                                true,
                                false
                        )
                );
            }
        }
    }
}

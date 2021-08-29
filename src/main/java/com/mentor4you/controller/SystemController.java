package com.mentor4you.controller;

import com.mentor4you.model.Accounts;
import com.mentor4you.model.GroupServices;
import com.mentor4you.model.Role;
import com.mentor4you.model.User;
import com.mentor4you.repository.AccountRepository;
import com.mentor4you.repository.GroupServicesRepository;
import com.mentor4you.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/system")
public class SystemController {

    @Autowired
    private final GroupServicesRepository groupServicesRepository;
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    public SystemController(GroupServicesRepository groupServicesRepository,
                            UserRepository userRepository,
                            AccountRepository accountRepository) {
        this.groupServicesRepository = groupServicesRepository;
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
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
        int numberAllUsers = 1 + NUMBER_ADMINS + NUMBER_MODERATORS + NUMBER_USERS;

        createAdmin(NUMBER_ADMINS);
        creatModerators(NUMBER_MODERATORS);
        createUsers(NUMBER_USERS);
        createAccounts(numberAllUsers);
//        writeMentorsInTable(numberAllUsers);
        return "tables added";
    }

    @GetMapping("/getMentorsAll")
    public List<User> getMentorsAll() {
        return userRepository.findAll();
    }


    //Create admin
    private void createAdmin(int numberAdmins) {
        for (int i = 1; i <= numberAdmins; i++) {
            userRepository.save(
                    new User(
                            Role.ADMIN,
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
        for (int i = 1; i <= numberModerators; i++) {
            userRepository.save(
                    new User(
                            Role.MODERATOR,
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
    private void createUsers(int numberUsers) {

        for (int i = 1; i <= numberUsers; i++) {
            userRepository.save(
                    new User(

                            Role.MENTEE,
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

    //CreateMentors
//    private void writeMentorsInTable(int numberAllusers) {
//        System.out.println("inside WriteMentors");
//        int GROUP_SERVICES = 1;
//        int MENTORS_ROLE = 3;
//        GroupServices groupService =
//                groupServicesRepository.findById(GROUP_SERVICES).get();
//
//        for (int i = 1; i < numberAllusers; i++) {
//            Accounts account = accountRepository.findById(i).get();
//            int rr = account.getUser().getRole_id().getId();
//
//            //check to ROLE in account
//            //if is it "Mentor" > creat
//            if (MENTORS_ROLE == (account.getUser().getRole_id().getId())) {
//                mentorRepository.save(
//                        new Mentors(
//                                account,
//                                "description",
//                                true,
//                                groupService,
//                                true,
//                                true,
//                                false
//                        )
//                );
//            }
//        }
//    }
}

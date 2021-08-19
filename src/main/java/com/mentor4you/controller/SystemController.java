package com.mentor4you.controller;

import com.mentor4you.model.*;
import com.mentor4you.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Properties;


@RestController
@RequestMapping("/system")
public class SystemController {

    @Autowired
    private RoleRepository roleRepository;
    private GroupServicesRepository groupServicesRepository;
    private UserRepository userRepository;
    private AccountRepository accountRepository;
    private MentorRepository mentorRepository;

    public SystemController(RoleRepository roleRepository,
                            GroupServicesRepository groupServicesRepository,
                            UserRepository userRepository,
                            AccountRepository accountRepository,
                            MentorRepository mentorRepository) {
        this.roleRepository = roleRepository;
        this.groupServicesRepository = groupServicesRepository;
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.mentorRepository = mentorRepository;
    }

    @GetMapping("/add")
    //TODO удалить или сделать закрытым после тестов
    /**
     * Создает таблицы Roles  GroupServices Accounts Users Mentors
     *
     * Записывает данные в Roles  GroupServices Accounts Users Mentors
     * результат  -->>  1 админ \\ 2 модератора \\ 15 менторов
     */
    public String registerRoles() {
        roleRepository.save(new Roles("notAuthorised"));
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

        createAdmin(NUMBER_ADMINS);
        creatModerators(NUMBER_MODERATORS);
        createUsers(NUMBER_USERS, 4);  // 4 -> Mentors Role
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
        int ROLES_ID = 2; // admin
        Roles role;
        role = roleRepository.findById(ROLES_ID).get();
        for (int i = 1; i <= numberAdmins; i++) {
            userRepository.save(
                    new User(
                            role,
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
        int ROLES_ID = 3; //moderator
        Roles role;
        role = roleRepository.findById(ROLES_ID).get();
        for (int i = 1; i <= numberModerators; i++) {
            userRepository.save(
                    new User(
                            role,
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

        Roles role;
        role = roleRepository.findById(roles).get();
        for (int i = 1; i <= numberUsers; i++) {
            userRepository.save(
                    new User(
                            role,
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
    private void writeMentorsInTable(int numberAllusers) {
        System.out.println("inside WriteMentors");
        int GROUP_SERVICES = 1;
        int MENTORS_ROLE = 4;
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

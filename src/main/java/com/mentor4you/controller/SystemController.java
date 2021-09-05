package com.mentor4you.controller;

import com.mentor4you.model.*;
import com.mentor4you.repository.*;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Random;



@RestController
@RequestMapping("/system")
public class SystemController {

    @Autowired
    private final GroupServicesRepository groupServicesRepository;
    private final AccountRepository accountRepository;
    private final MentorRepository mentorRepository;
    private final SocialNetworksRepository socialNetworksRepository;
    private final Links_to_accountsRepository links_to_accountsRepository;
    private final LanguagesRepository languagesRepository;
    private final MenteeRepository menteeRepository;

    public SystemController(GroupServicesRepository groupServicesRepository,
                            AccountRepository accountRepository,
                            MentorRepository mentorRepository,
                            SocialNetworksRepository socialNetworksRepository,
                            Links_to_accountsRepository links_to_accountsRepository,
                            LanguagesRepository languagesRepository, MenteeRepository menteeRepository) {
        this.groupServicesRepository = groupServicesRepository;
        this.accountRepository = accountRepository;
        this.mentorRepository = mentorRepository;
        this.languagesRepository = languagesRepository;
        this.socialNetworksRepository = socialNetworksRepository;
        this.links_to_accountsRepository = links_to_accountsRepository;
        this.menteeRepository = menteeRepository;
    }


    @Operation(summary = "method add 1 admin, 3 moderators and 15 Mentors on you DB")
    @GetMapping("/add")
    public String registerRoles() {

        try {
            groupServicesRepository.save(new GroupServices("No"));
            groupServicesRepository.save(new GroupServices("Yes"));
            groupServicesRepository.save(new GroupServices("Mix"));

            int NUMBER_ADMINS = 1;
            int NUMBER_MODERATORS = 3;
            int NUMBER_MENTORS = 15;
            int NUMBER_MENTEES = 15;

            createAdmin(NUMBER_ADMINS);
            createModerators(NUMBER_MODERATORS);
            createMentors(NUMBER_MENTORS);
            createMentees(NUMBER_MENTEES);
            createLanguages();
            createSocialNetworks();

            //connects mentors with social networks
            setSocNetworkToMentor_Test();


            return "tables added";
        } catch (Exception ex) {
            return "The DataBase might have a set of test values, and method  " +
                    "\"GET http://localhost:8080/system/add\" " +
                    "was already called   \n \n Error:   " + ex.getMessage();
        }

    }


    //Create admin
    private void createAdmin(int numberAdmins) {
        for (int i = 1; i <= numberAdmins; i++) {

            User user = createOneUser(i, Role.ADMIN);
            accountRepository.save(createOneAccount(user, i));
        }
    }

    //Create moderators
    private void createModerators(int numberModerators) {
        for (int i = 1; i <= numberModerators; i++) {

            User user = createOneUser(i, Role.MODERATOR);
            accountRepository.save(createOneAccount(user, i));
        }

    }
    //CreateLanguages
    private void createLanguages(){

        languagesRepository.save(new Languages("ukrainian"));
        languagesRepository.save(new Languages("english"));
        languagesRepository.save(new Languages("russian"));
        languagesRepository.save(new Languages("polish"));
        languagesRepository.save(new Languages("сzech"));

    }

    private void createMentors(int numberOfMentors) {
        for (int i = 1; i <= numberOfMentors; i++) {

            User user = createOneUser(i, Role.MENTOR);

            Mentors m = new Mentors();
            m.setAccounts(createOneAccount(user, i));
            m.setDescription("description");
            m.setShowable_status(true);
            m.isIs_online(true);
            m.isIs_offline_in(true);
            m.isIs_offline_out(true);
            m.setEducations(Arrays.asList(new Educations(i+"edu"),new Educations(i+"edu_other")));
            m.setCertificats(Arrays.asList(new Certificats(i+"cert"),new Certificats(i+"cert_other")));

            mentorRepository.save(m);
        }
    }
    private void createMentees(int numberOfMentees) {
        for (int i = 1; i <= numberOfMentees; i++) {

            User user = createOneUser(i, Role.MENTEE);

            Mentees m = new Mentees();
            m.setAccounts(createOneAccount(user, i));


            menteeRepository.save(m);
        }
    }

    private Accounts createOneAccount(User user, int i) {

        Accounts a = new Accounts();

        a.setUser(user);
        a.setPhoneNumber("(" + i + ")" + i + i + i + i + i + "");
        a.setLast_visit(LocalDateTime.now());

        return a;
    }

    private User createOneUser(int i, Role role) {

        User n = new User();
        n.setEmail(i + "_" + role.name() + "@email");
        n.setPassword(i + "_" + role.name() + "password");
        n.setFirst_name(i + "_" + role.name() + "FN");
        n.setLast_name(i + "_" + role.name() + "LN");
        n.setRegistration_date(LocalDateTime.now());
        n.setStatus(true);
        n.setRole(role);

        return n;
    }

    //CreateSocialNetworks
    private void createSocialNetworks() {
        String[] arrSocNet = new String[]{"LinkedIn", "FaceBook", "Telegram"};

        for (String socNetName : arrSocNet) {

            Social_networks social_networks = new Social_networks();
            social_networks.setName(socNetName);

            socialNetworksRepository.save(social_networks);
        }
    }

    private void setSocNetworkToMentor_Test() {
        // check exist users with Role.MENTOR
        int theMentors = accountRepository.findByRole(Role.MENTOR).size();

        // find all users with Role.MENTOR
        if (theMentors != 0) {
            List<Accounts> allMentor = accountRepository.findByRole(Role.MENTOR);
            int linkId = 1; // id=1  -->  LinkedIn

            for (Accounts accounts : allMentor) {
                int accountId = accounts.getId();

                Links_to_accounts links_to_accounts = new Links_to_accounts();

                links_to_accounts.setSocial_networks(socialNetworksRepository.getById(linkId));
                links_to_accounts.setAccounts(accounts);
                links_to_accounts.setUrl((socialNetworksRepository.findById(linkId).
                        get().getName()) + "_" + Role.MENTOR.name()
                        + "_Id_" + accountId);

                links_to_accountsRepository.save(links_to_accounts);

            }
        }
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
}

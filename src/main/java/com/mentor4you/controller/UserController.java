package com.mentor4you.controller;

import com.mentor4you.model.Role;
import com.mentor4you.model.User;
import com.mentor4you.repository.RoleRepository;
import com.mentor4you.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@RestController
@RequestMapping("api/add")
public class UserController {

    @Autowired
    private UserRepository userReposetory;
    @Autowired
    private RoleRepository roleRepository;

    public UserController(UserRepository userReposetory) {
        this.userReposetory = userReposetory;
    }

    @GetMapping("/addAdmin")
    public String registerRoles() throws ParseException {
        int ROLES_ID = 4;

        Role role = roleRepository.findById(ROLES_ID).get();
        userReposetory.save(
                new User(
                        role,
                        "String email",
                        "String password",
                        "String first_name",
                        "String last_name",
                        "String avatar",
                        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
                                .parse(getCurentTime()),
                        true
                      )
                );
         return "user aded";
    }
    private String getCurentTime(){
            String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(System.currentTimeMillis());
            return timeStamp;
        }
}

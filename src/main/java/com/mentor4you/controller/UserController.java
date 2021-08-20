package com.mentor4you.controller;

import com.mentor4you.model.Accounts;
import com.mentor4you.model.Roles;
import com.mentor4you.model.User;
import com.mentor4you.repository.AccountRepository;
import com.mentor4you.repository.RoleRepository;
import com.mentor4you.repository.UserRepository;
import com.mentor4you.service.UserService;
import com.mentor4you.service.requests.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //select all accounts
    @GetMapping
    List<Accounts> getAllUsers(){
        return userService.getAllUsers();
    }

    //create new account /w user data /w role
    @PostMapping("/register")
    String register(@RequestBody RegisterRequest request){
        return userService.register(request);
    }

}

package com.mentor4you.controller;

import com.mentor4you.model.User;
import com.mentor4you.service.UserService;
import com.mentor4you.model.DTO.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    //create new account /w user data /w role
    @PostMapping("/register")
    String register(@RequestBody RegisterRequest request){
        return userService.register(request);
    }

}

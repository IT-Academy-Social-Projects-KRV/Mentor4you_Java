package com.mentor4you.controller;

import com.mentor4you.model.User;
import com.mentor4you.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}

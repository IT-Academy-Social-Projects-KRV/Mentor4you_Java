package com.mentor4you.controller;

import com.mentor4you.model.DTO.PasswordDTO;
import com.mentor4you.model.User;
import com.mentor4you.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "select all users")
    @GetMapping
    List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @Operation(summary = "change password")
    @PutMapping("/changePassword")
    String changePassword(@RequestHeader("Authorization") String token, @RequestBody PasswordDTO request){
        return userService.changePassword(token, request.getPassword());
    }
}

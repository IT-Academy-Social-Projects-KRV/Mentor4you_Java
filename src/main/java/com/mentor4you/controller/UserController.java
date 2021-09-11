package com.mentor4you.controller;

import com.mentor4you.model.DTO.EmailRequest;
import com.mentor4you.model.DTO.PasswordDTO;
import com.mentor4you.model.User;
import com.mentor4you.repository.UserRepository;
import com.mentor4you.service.EmailService;
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
    UserRepository userRepository;
    EmailService emailService;

    public UserController(UserService userService, UserRepository userRepository, EmailService emailService) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.emailService = emailService;
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

    @PostMapping("/updateEmail")
    public String updateEmail(@RequestBody EmailRequest request){

        String email = request.getEmail();
        int id = request.getId();
        //TODO check email to valid with sending testEmail
        if (emailService.isEmailValidRegEx(email)){

            if (userRepository.findByEmail(email).isEmpty()) {

                User userToUpdate = userRepository.findById(id).get();
                userToUpdate.setEmail(email);
                userRepository.save(userToUpdate);

                return "Email updated to "+ userRepository.findById(id).get().getEmail();
            }
            else { return "email "+email+" is exist";}
        }
        else {return "Something wrong with thr email ->  "+email;}
    }


}

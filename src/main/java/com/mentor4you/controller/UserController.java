package com.mentor4you.controller;

import com.mentor4you.exception.RegistrationException;
import com.mentor4you.model.DTO.EmailRequest;
import com.mentor4you.model.DTO.PasswordDTO;
import com.mentor4you.model.DTO.UserBanDTO;
import com.mentor4you.model.DTO.UserBanUpdateRequest;
import com.mentor4you.model.Mentees;
import com.mentor4you.model.User;
import com.mentor4you.repository.UserRepository;
import com.mentor4you.service.EmailService;
import com.mentor4you.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


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
    List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @Operation(summary = "change password")
    @PutMapping("/changePassword")
    ResponseEntity<?> changePassword(@RequestBody PasswordDTO dto,
                                     HttpServletRequest request) {

        Map<String, String> res = new HashMap<>();
        try {
            String result = userService.changePassword(request, dto.getOldPassword(), dto.getNewPassword());
            res.put("message", result);
            return ResponseEntity.ok(res);
        } catch (RegistrationException | UsernameNotFoundException e) {
            res.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(res);
        }
    }


    @PostMapping("/updateEmail")
    public String updateEmail(@RequestBody EmailRequest request) {
        return emailService.updateEmail(request.getEmail(), request.getId());
    }

    @Operation(summary = "get banned user")
    @GetMapping("/getAllBannedUser")
    ResponseEntity<List<UserBanDTO>> getAllBannedUser() {
        List<UserBanDTO> list = userService.getAllBannedUsers(true);
        if (list.isEmpty()) {
            return new ResponseEntity(list, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity(list, HttpStatus.OK);
        }
    }


    @Operation(summary = "change User's ban status")
    @PutMapping("/changeBanToUser")
    ResponseEntity<?> changeBanToUser(@RequestBody UserBanUpdateRequest dto) {
        String result = userService.changeBanToUser(dto.banStatus, dto.getId());
        return new ResponseEntity<String>(result, HttpStatus.OK);
    }


    @Operation(summary = "change User's name")
    @PutMapping("/changeUser")
    ResponseEntity<?> changeUser() {
        userRepository.updateUser("Change4", 5, "1_MENTOR@email.com");

        return new ResponseEntity<String>(HttpStatus.OK);
    }
}

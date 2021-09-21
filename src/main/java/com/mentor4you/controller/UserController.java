package com.mentor4you.controller;

import com.mentor4you.exception.RegistrationException;
import com.mentor4you.model.DTO.EmailRequest;
import com.mentor4you.model.DTO.PasswordDTO;
import com.mentor4you.model.DTO.AvatarUpdateRequest;
import com.mentor4you.model.User;
import com.mentor4you.repository.UserRepository;
import com.mentor4you.service.EmailService;
import com.mentor4you.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
    ResponseEntity<?> changePassword(@RequestBody PasswordDTO dto, HttpServletRequest request){

        Map<String, String> res = new HashMap<>();
        try{
            String result = userService.changePassword(request, dto.getOldPassword(), dto.getNewPassword());
            res.put("message",result);
            return ResponseEntity.ok(res);
        }catch (RegistrationException | UsernameNotFoundException e){
            res.put("message",e.getMessage());
            return ResponseEntity.badRequest().body(res);
        }
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
        else {
            return "Something wrong with thr email ->  "+email;
        }
    }

//    @PostMapping("/updateAvatar")
//    public String updateAvatar(@RequestHeader String token, @RequestBody AvatarUpdateRequest dto){
//        return userService.changeAvatar(token, dto.getAvatarURL());
//    }

    @PostMapping("/updateAvatar")
    public String updateAvatar(@RequestBody AvatarUpdateRequest dto){
        return userService.changeAvatar(dto.getToken(), dto.getAvatarURL());
    }
}

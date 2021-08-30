package com.mentor4you.service;

import com.mentor4you.model.DTO.UserDTO;
import com.mentor4you.model.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RegistrationService {

    public String registration(UserDTO userDTO) {

//        User user = new User();
//
//        user.setEmail(userDTO.getEmail());
//
//        user.setPassword(userDTO.getPassword());
//
//        user.setRegistration_date(LocalDateTime.now());
//
//        user.setStatus(true);

//        if(!userDTO.isRole()){ //false
//            //role mentee
////            user.setRole_id(Role.MENTEE);
//
//        }else{
//            //role mentor
////            user.setRole_id(MENTOR);
//        }

        return "User created";
    }
}

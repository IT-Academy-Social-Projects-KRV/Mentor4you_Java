package com.mentor4you.service;

import com.mentor4you.exception.RegistrationException;
import com.mentor4you.model.User;
import com.mentor4you.repository.UserRepository;
import com.mentor4you.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    PasswordService passwordService;

    @Autowired
    UserRepository userRepository;
    JwtProvider jwtProvider;

    public UserService(UserRepository userRepository, JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
    }

    //select all users
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public String changePassword(String token, String password) {

        if(!passwordService.isValidPassword(password)){
            throw new RegistrationException("Password is not valid");
        }

        User user = userRepository.findUserByEmail(jwtProvider.getLoginFromToken(token));

        user.setPassword(passwordService.encodePassword(password));

        userRepository.save(user);

        return "Password changed";
    }
}

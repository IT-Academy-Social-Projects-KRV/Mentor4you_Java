package com.mentor4you.service;

import com.mentor4you.exception.RegistrationException;
import com.mentor4you.model.User;
import com.mentor4you.repository.UserRepository;
import com.mentor4you.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class UserService {

    @Autowired
    PasswordService passwordService;
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

    public String changePassword(HttpServletRequest request, String oldPassword, String newPassword) {
        String token = jwtProvider.getTokenFromRequest(request);
        String email =jwtProvider.getLoginFromToken(token);

        User user = userRepository.findUserByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found ");
        }
        if(passwordService.equalsPassword(oldPassword, user.getPassword())){
            if(!passwordService.isValidPassword(newPassword)){
                throw new RegistrationException("Password is not valid");
            }

            user.setPassword(passwordService.encodePassword(newPassword));

            userRepository.save(user);
            return "Password changed";
        }else{
            throw new RegistrationException("The old password is incorrect");
        }
    }
}

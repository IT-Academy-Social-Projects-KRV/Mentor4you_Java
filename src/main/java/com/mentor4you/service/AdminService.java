package com.mentor4you.service;

import com.mentor4you.model.Role;
import com.mentor4you.model.User;
import com.mentor4you.repository.UserRepository;
import com.mentor4you.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    UserRepository userRepository;
    JwtProvider jwtProvider;

    public AdminService(UserRepository userRepository, JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
    }

    public String appointModerator(String email){
        User user = userRepository.findUserByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User is not found.");
        }
        user.setRole(Role.MODERATOR);
        userRepository.save(user);
        return "New moderator is appointed";
    }
}

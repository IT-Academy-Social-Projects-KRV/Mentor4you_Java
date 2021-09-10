package com.mentor4you.service;

import com.mentor4you.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    UserRepository userRepository;

    public EmailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //check user email is existing in database
    public boolean emailExist(String email){
        return userRepository.findByEmail(email).isPresent();
    }
}

package com.mentor4you.service;

import com.mentor4you.model.User;
import com.mentor4you.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;

    }

    //select all users
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    //create new account /w user data /w role
}

package com.mentor4you.service;

import com.mentor4you.model.User;
import com.mentor4you.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class MentorService {

    @Autowired
    UserRepository userRepository;

    public MentorService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    //select all mentor
    public List<User> getAllMentors(){
        return userRepository.findByRoleName("Mentor");
    }

    //select mentor by id
    public Optional<User> getMentorById(int id){
        return userRepository.findById(id);
    }
}

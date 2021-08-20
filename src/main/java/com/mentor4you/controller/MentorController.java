package com.mentor4you.controller;

import com.mentor4you.model.User;
import com.mentor4you.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mentors")
public class MentorController {

    @Autowired
    UserRepository userRepository;

    public MentorController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //select all mentor
    @GetMapping
    List<User> getAllMentor(){
        return userRepository.findByRoleName("Mentor");
    }
    //select mentor by id
    @GetMapping("/{id}")
    Optional<User> getMentorById(@PathVariable(value = "id") Integer id){
        return userRepository.findById(id);
    }

}

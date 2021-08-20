package com.mentor4you.controller;

import com.mentor4you.model.User;
import com.mentor4you.service.MentorService;
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
    MentorService mentorService;

    public MentorController(MentorService mentorService) {
        this.mentorService = mentorService;
    }

    //select all mentor
    @GetMapping
    List<User> getAllMentor(){
        return mentorService.getAllMentors();
    }

    //select mentor by id
    @GetMapping("/{id}")
    Optional<User> getMentorById(@PathVariable(value = "id") Integer id){
        return mentorService.getMentorById(id);
    }

}

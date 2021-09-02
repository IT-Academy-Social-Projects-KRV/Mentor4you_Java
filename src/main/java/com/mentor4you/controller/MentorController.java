package com.mentor4you.controller;

import com.mentor4you.exception.ErrorObject;
import com.mentor4you.exception.MentorNotFoundException;
import com.mentor4you.model.Accounts;
import com.mentor4you.service.MentorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mentors")
public class MentorController {

    @Autowired
    private final MentorService mentorService;

    public MentorController(MentorService mentorService) {
        this.mentorService = mentorService;
    }

    //select all mentor
    @GetMapping("/")
    public List<Accounts> getAllMentor(){
        return mentorService.getAllMentors();
    }

    //select mentor by id
    @GetMapping("/{id}")
    public Optional<Accounts> getMentorById(@PathVariable(value = "id") Integer id){
        return mentorService.getMentorById(id);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorObject> handleException(MentorNotFoundException ex) {
        ErrorObject eObject = new ErrorObject();
        eObject.setStatus(HttpStatus.NOT_FOUND.value());
        eObject.setMessage(ex.getMessage());
        eObject.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(eObject, HttpStatus.NOT_FOUND);
    }

    //select mentor by id
    @GetMapping("/{account_id}/addlanguages/{languages_id}")
    public int addLanguagesForMentor(@PathVariable(value = "account_id") Integer account_id,
                                       @PathVariable(value = "languages_id") Integer languages_id){
        mentorService.addLanguages(account_id,languages_id);
        return languages_id;
    }

    @GetMapping("/{account_id}/removelanguages/{languages_id}")
    public Accounts removeLanguagesForMentor(@PathVariable(value = "account_id") Integer account_id,
                                       @PathVariable(value = "languages_id") Integer languages_id){
        return mentorService.removeLanguages(account_id,languages_id);
//        return languages_id;
    }
}

package com.mentor4you.controller;

import com.mentor4you.exception.ErrorObject;
import com.mentor4you.exception.MentorNotFoundException;
import com.mentor4you.model.Mentors;
import com.mentor4you.model.Mentors_to_categories;
import com.mentor4you.service.MentorService;
import com.mentor4you.service.requests.MentorGeneralDTO;
import io.swagger.v3.oas.annotations.Operation;
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
    MentorService mentorService;

    public MentorController(MentorService mentorService) {
        this.mentorService = mentorService;
    }



    //select mentor by id
   /* @Operation(summary = "select mentor by id")
    @GetMapping("/{id}")
    Optional<Mentors> getMentorById(@PathVariable(value = "id") Integer id){
        return mentorService.getMentorById(id);
    }*/
    @GetMapping("/{email}")
    ResponseEntity<MentorGeneralDTO> getMentorById(@PathVariable(value = "email") String email){
        return mentorService.getByEmail(email);}

    @Operation(summary = "Full info about mentors", description = "This method provides the most complete information about existing mentors")
    @GetMapping
    List<Mentors> getAllMentor(){
        return mentorService.getFullInfoAllMentors();
    }

    @PutMapping("/{email}")
    ResponseEntity<String> update(@PathVariable(value = "email") String email, @RequestBody MentorGeneralDTO up){
        return mentorService.updateGeneralDataMentors(email,up);}


    @ExceptionHandler
    public ResponseEntity<ErrorObject> handleException(MentorNotFoundException ex) {
        ErrorObject eObject = new ErrorObject();
        eObject.setStatus(HttpStatus.NOT_FOUND.value());
        eObject.setMessage(ex.getMessage());
        eObject.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(eObject, HttpStatus.NOT_FOUND);
    }

}

package com.mentor4you.controller;

import com.mentor4you.exception.ErrorObject;
import com.mentor4you.exception.MentorNotFoundException;
import com.mentor4you.model.Mentees;
import com.mentor4you.model.Mentors;
import com.mentor4you.service.MenteeService;
import com.mentor4you.service.MentorService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;



@RestController
@RequestMapping("/api/mentees")
public class MenteesController {

        @Autowired
        MenteeService menteesService;

        public MenteesController(MenteeService menteesService) {
                this.menteesService = menteesService;
        }

        //select mentees by id
        @GetMapping("/{id}")
        Optional<Mentees> getMentorById(@PathVariable(value = "id") Integer id){
            return menteesService.getMentorById(id);
        }
        @Operation(summary = "info about mentees")
        @GetMapping
        List<Mentees> getAllMentor(){
            return menteesService.getFullInfoAllMentees();
        }





}

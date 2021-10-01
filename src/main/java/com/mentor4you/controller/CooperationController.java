package com.mentor4you.controller;

import com.mentor4you.model.DTO.DTOforCop;
import com.mentor4you.service.CooperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

@RestController
@RequestMapping("/api/coop")
public class CooperationController {

    @Autowired
    CooperationService cooperationService;


    CooperationController(CooperationService cooperationService){
        this.cooperationService = cooperationService;
    }
    @PreAuthorize("hasAuthority('MENTEE')")
    @PostMapping("/{id}")
    public ResponseEntity<String> createCooperation(@PathVariable(value = "id") Integer id, HttpServletRequest request){
       return  cooperationService.createCooperation(id,request);
    }

    @GetMapping("/mentorCooperation")
    public ResponseEntity<Set<DTOforCop>> getCooperationForMentor(HttpServletRequest request){
        return  cooperationService.getCooperation(request);
    }

    @PutMapping("/approve/{id},{status}")
    public ResponseEntity<String> decisionsOnCoop(@PathVariable(value = "id")Integer id,@PathVariable(value = "status")Boolean status,HttpServletRequest request){
        return  cooperationService.decisionsOnCoop(request,id,status);
    }
}

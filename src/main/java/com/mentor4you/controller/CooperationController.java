package com.mentor4you.controller;

import com.mentor4you.model.DTO.coopDTO.CoopStatus;
import com.mentor4you.model.DTO.coopDTO.DTOforCopUser;
import com.mentor4you.model.DTO.coopDTO.DTOstatusCoopMentee;
import com.mentor4you.service.CooperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

@RestController
@RequestMapping("/api/Cooperation")
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
    public ResponseEntity<Set<DTOforCopUser>> getCooperationForMentor(HttpServletRequest request){
        return  cooperationService.getCooperationForMentor(request);
    }

    @GetMapping("/menteeCooperation")
    public ResponseEntity<Set<DTOstatusCoopMentee>> getCooperationForMentee(HttpServletRequest request){
        return  cooperationService.getCooperationForMentee(request);
    }

    @PutMapping("/approve/{id}")
    public ResponseEntity<String> decisionsOnCoop(@PathVariable(value = "id")Integer id,@RequestBody Boolean status,HttpServletRequest request){
        return  cooperationService.decisionsOnCoop(request,id,status);
    }

    @PutMapping("/responseMentee/{id}")
    public  ResponseEntity<String> responseMentee(@PathVariable(value = "id")Integer id,HttpServletRequest request){
        return cooperationService.responseMentee(request,id);
    }
}

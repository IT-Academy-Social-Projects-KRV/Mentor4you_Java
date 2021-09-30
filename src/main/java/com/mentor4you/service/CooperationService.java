package com.mentor4you.service;


import com.mentor4you.repository.CooperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CooperationService {
    @Autowired
    CooperationRepository cooperationRepository;

    public CooperationService(CooperationRepository cooperationRepository) {
        this.cooperationRepository = cooperationRepository;
    }

    public ResponseEntity<String> createCooperation(int id,String email){

        return new ResponseEntity<String>(HttpStatus.OK);
    }
}

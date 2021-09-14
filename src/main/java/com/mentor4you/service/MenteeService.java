package com.mentor4you.service;

import com.mentor4you.exception.MenteeNotFoundException;
import com.mentor4you.exception.MentorNotFoundException;
import com.mentor4you.model.Mentees;
import com.mentor4you.model.Mentors;
import com.mentor4you.model.Role;
import com.mentor4you.repository.AccountRepository;
import com.mentor4you.repository.MenteeRepository;
import com.mentor4you.repository.MentorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class MenteeService {
    @Autowired
    AccountRepository accountRepository;
    MenteeRepository menteeRepository;

    public MenteeService(AccountRepository accountRepository, MenteeRepository menteeRepository) {
        this.accountRepository = accountRepository;
        this.menteeRepository = menteeRepository;
    }

    public List<Mentees> getFullInfoAllMentees(){
        int theMentees = accountRepository.findByRole(Role.MENTEE).size();
        if(theMentees!=0){
            return menteeRepository.findAll();
        }
        throw new MentorNotFoundException("Mentees not found");

    }

    public Optional<Mentees> getMenteeById(int id){
        Optional<Mentees> theMentee = menteeRepository.findById(id).stream().filter(e->e.getId()==id).findFirst();
        if(theMentee.isPresent()) {
            return theMentee;
        }
        throw new MenteeNotFoundException("Mentee with id = "+ id +" not found");
    }
}

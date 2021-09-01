package com.mentor4you.service;

import com.mentor4you.exception.MentorNotFoundException;
import com.mentor4you.model.Accounts;
import com.mentor4you.model.Role;
import com.mentor4you.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class MentorService {

    @Autowired
    AccountRepository accountRepository;


    public MentorService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    //select all mentor
    public List<Accounts> getAllMentors(){
        int theMentors = accountRepository.findByRole().size();
        if(theMentors!=0){
            return accountRepository.findByRole();
        }
        throw new MentorNotFoundException("Mentors not found");

    }


//    select mentor by id
    public Optional<Accounts> getMentorById(int id){

        Optional<Accounts> theMentor = accountRepository.findById(id).stream().filter(e->e.getId()==id).findFirst();
        if(theMentor.isPresent()) {
            return theMentor;
        }

        throw new MentorNotFoundException("Mentor with id = "+ id +" not found");

    }
}

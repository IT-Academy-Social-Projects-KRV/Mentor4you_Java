package com.mentor4you.service;

import com.mentor4you.model.Accounts;
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
//        return userRepository.findByRoleName("Mentor");
        return accountRepository.findByRoleName("Mentor");
    }


//    select mentor by id
    public Optional<Accounts> getMentorById(int id){
        return accountRepository.findById(id);
    }
}

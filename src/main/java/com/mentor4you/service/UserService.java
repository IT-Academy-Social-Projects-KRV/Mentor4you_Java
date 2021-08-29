package com.mentor4you.service;

import com.mentor4you.model.Accounts;
import com.mentor4you.model.Mentors;
import com.mentor4you.model.User;
import com.mentor4you.repository.AccountRepository;
import com.mentor4you.repository.MentorRepository;
import com.mentor4you.repository.UserRepository;
import com.mentor4you.service.requests.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    AccountRepository accountRepository;
    MentorRepository mentorRepository;

    public UserService(UserRepository userRepository, AccountRepository accountRepository, MentorRepository mentorRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.mentorRepository = mentorRepository;
    }

    //select all users
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    //create new account /w user data /w role
    public String register(RegisterRequest request){

        User n = new User();

        n.setEmail(request.getEmail());
        n.setPassword(request.getPassword());
        n.setFirst_name(request.getFirst_name());
        n.setLast_name(request.getLast_name());
        n.setRegistration_date(LocalDateTime.now());
        n.setStatus(true);

        Accounts a = new Accounts();
        Mentors m = new Mentors();

        if(!request.getRoles_id()){
            n.setRole(com.mentor4you.model.Role.MENTEE);
            a.setUser(n);
            accountRepository.save(a);
        }else{
            n.setRole(com.mentor4you.model.Role.MENTOR);
            a.setUser(n);
            m.setAccounts(a);
            mentorRepository.save(m);
        }


        return "User created";
    }
}

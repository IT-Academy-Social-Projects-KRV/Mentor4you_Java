package com.mentor4you.controller;

import com.mentor4you.model.Accounts;
import com.mentor4you.model.Roles;
import com.mentor4you.model.User;
import com.mentor4you.repository.AccountRepository;
import com.mentor4you.repository.RoleRepository;
import com.mentor4you.repository.UserRepository;
import com.mentor4you.service.requests.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserRepository userRepository;
    RoleRepository roleRepository;
    AccountRepository accountRepository;

    public UserController(UserRepository userRepository, RoleRepository roleRepository, AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.accountRepository = accountRepository;
    }

    //select all accounts
    @GetMapping
    List<Accounts> getAllUsers(){
        return accountRepository.findAll();
    }

    //create new account /w user data /w role
    @PostMapping("/register")
    String register(@RequestBody RegisterRequest request){

        User n = new User();

        n.setEmail(request.getEmail());
        n.setPassword(request.getPassword());
        n.setFirst_name(request.getFirst_name());
        n.setLast_name(request.getLast_name());
        n.setRegistration_date(LocalDateTime.now());
        n.setStatus(true);

        Roles role;
        if(!request.getRoles_id()){
            role = roleRepository.getById(4);
        }else{
            role = roleRepository.getById(3);
        }
        n.setRole_id(role);

        Accounts a = new Accounts();
        a.setPhoneNumber(request.getPhone_number());
        a.setUser(n);

        accountRepository.save(a);

        return "User created";
    }

}

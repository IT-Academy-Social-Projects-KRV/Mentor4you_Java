package com.mentor4you.service;

import com.mentor4you.model.Accounts;
import com.mentor4you.model.Roles;
import com.mentor4you.model.User;
import com.mentor4you.repository.AccountRepository;
import com.mentor4you.repository.RoleRepository;
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
    RoleRepository roleRepository;
    AccountRepository accountRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.accountRepository = accountRepository;
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

package com.mentor4you.controller;


import com.mentor4you.model.Account;
import com.mentor4you.model.User;
import com.mentor4you.repository.AccountRepository;
import com.mentor4you.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@RestController
@RequestMapping("api/add")
public class AccountController {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserRepository userRepository;

    public AccountController(AccountRepository accountRepository,
                             UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/addAccount")
    public String registerRoles() throws ParseException {
        Long USER_ID = 1l;

        User user = userRepository.findById(USER_ID).get();
        accountRepository.save(
                new Account(
                        user,
                        "5675675675",
                        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                                .parse(getCurentTime())
                )
        );
        return "Account aded";
    }
    private String getCurentTime(){
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());
        return timeStamp;
    }
}

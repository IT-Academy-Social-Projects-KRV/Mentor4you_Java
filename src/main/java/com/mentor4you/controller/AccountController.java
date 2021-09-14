package com.mentor4you.controller;

import com.mentor4you.model.Accounts;
import com.mentor4you.model.DTO.PhoneDTO;
import com.mentor4you.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class AccountController {
    @Autowired
    AccountRepository accountRepository;

    public AccountController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @PostMapping("/updatePhoneNumber")
    public String updatePhoneNumber(@RequestBody PhoneDTO request) {
        String phoneNumber = request.getPhoneNumber();
        int id = request.getId();
        if (accountRepository.findByPhone(phoneNumber).isEmpty()){
            Accounts accounts = accountRepository.findById(id);
            //Accounts accounts = accountRepository.findById(id);
            accounts.setPhoneNumber(phoneNumber);
            accountRepository.save(accounts);
            return  "Phone number to "+ accountRepository.findMentorById(id).get().getPhoneNumber();
        }
        else {
            return "PhoneNumber " + phoneNumber + " already exists";
        }
    }
}


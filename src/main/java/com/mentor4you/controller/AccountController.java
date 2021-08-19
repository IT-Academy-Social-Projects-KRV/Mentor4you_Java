package com.mentor4you.controller;

import com.mentor4you.model.Accounts;
import com.mentor4you.model.User;
import com.mentor4you.repository.AccountRepository;
import com.mentor4you.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

@RestController
@RequestMapping()
public class AccountController {}

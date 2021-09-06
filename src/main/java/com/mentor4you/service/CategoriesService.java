package com.mentor4you.service;

import com.mentor4you.exception.MentorNotFoundException;
import com.mentor4you.model.Categories;
import com.mentor4you.model.Cooperation;
import com.mentor4you.model.Mentees;
import com.mentor4you.model.Role;
import com.mentor4you.repository.AccountRepository;
import com.mentor4you.repository.MenteeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriesService {
    @Autowired
    AccountRepository accountRepository;
    MenteeRepository menteeRepository;
    Cooperation cooperation;



}

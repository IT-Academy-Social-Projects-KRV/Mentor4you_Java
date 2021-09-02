package com.mentor4you.controller;

import com.mentor4you.model.Accounts;
import com.mentor4you.model.Languages;
import com.mentor4you.repository.LanguagesRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/api/languages")
public class LanguagesController {
    private final LanguagesRepository languagesRepository;

    public LanguagesController(LanguagesRepository languagesRepository) {
        this.languagesRepository = languagesRepository;
    }

    @GetMapping("/")
    public List<Languages> getAllLanguages(){
        return languagesRepository.findAll();
    }
}

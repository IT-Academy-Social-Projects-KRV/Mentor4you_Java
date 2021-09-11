package com.mentor4you.controller;

import com.mentor4you.model.Languages;
import com.mentor4you.service.LanguagesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/languages")
public class LanguagesController {
    private final LanguagesService languagesService;

    public LanguagesController(LanguagesService languagesService) {
        this.languagesService = languagesService;
    }

    @GetMapping("/")
    public List<Languages> getAllLanguages(){
        return languagesService.getAllLanguages();
    }
}

package com.mentor4you.service;

import com.mentor4you.model.Languages;
import com.mentor4you.repository.LanguagesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguagesService {
    private final LanguagesRepository languagesRepository;

    public LanguagesService(LanguagesRepository languagesRepository) {
        this.languagesRepository = languagesRepository;
    }

    public List<Languages> getAllLanguages(){
        return languagesRepository.findAll();
    }
}

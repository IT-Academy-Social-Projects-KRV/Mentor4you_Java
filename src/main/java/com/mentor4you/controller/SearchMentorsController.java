package com.mentor4you.controller;

import com.mentor4you.model.DTO.serchMentorsDTO.SearchMentorsDTO;
import com.mentor4you.model.DTO.serchMentorsDTO.SmallDataMentorDTO;
import com.mentor4you.model.Mentors;
import com.mentor4you.repository.AccountRepository;
import com.mentor4you.repository.MentorRepository;
import com.mentor4you.service.SearchMentorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

import java.util.List;


@RestController
@RequestMapping("/api/searchMentor")
public class SearchMentorsController {


    @Autowired
    AccountRepository accountRepository;
    SearchMentorsService searchMentorsService;
    MentorRepository mentorRepository;

    public SearchMentorsController(AccountRepository accountRepository,
                             SearchMentorsService searchMentorsService,
                             MentorRepository mentorRepository) {
        this.accountRepository = accountRepository;
        this.searchMentorsService = searchMentorsService;
        this.mentorRepository = mentorRepository;
    }

    @Operation(summary = "")
    @GetMapping("/filterGetListSmallMentors/{city}/{categoryName}/{language}/{minPrice}/{maxPrice}")
    ResponseEntity<List<SmallDataMentorDTO>> fff(@PathVariable("city") String city,
                                 @PathVariable("categoryName") String categoryName,
                                 @PathVariable("language") String language,
                                 @PathVariable("minPrice") int minPrice,
                                 @PathVariable("maxPrice") int maxPrice
                                 ) {
        List<Mentors> mentList = mentorRepository.findMentorsByCategoryName(categoryName);
        if (mentList.size() > 0) {

           return searchMentorsService.createSmallMentorDTOFiltr(mentList, city, categoryName, language, minPrice, maxPrice);
        } else {
            return new ResponseEntity<List<SmallDataMentorDTO>>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "")
    @GetMapping("/findMentorsBestRating/{number}")
    ResponseEntity<List<SmallDataMentorDTO>> fff(@PathVariable("number") int number){
        List<Mentors> mentList = mentorRepository.findMentorsBestRating(PageRequest.of(0,number));
        if (mentList.size() > 0) {
            return searchMentorsService.createSmallMentorDTO(mentList);
        } else {
            return new ResponseEntity<List<SmallDataMentorDTO>>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Full info about categories, languages and countries")
    @GetMapping
    SearchMentorsDTO getAllMentor(){
        return searchMentorsService.createSerchMentorSTOList();
    }

    /*@Operation(summary = "")
    @GetMapping("/findMentorsBiId/{id}")
    ResponseEntity<List<SmallDataMentorDTO>> fff(@PathVariable("id") int id){
        List<Mentors> mentList = mentorRepository.findMentorsById(id);
        if (mentList.size() > 0) {
            return searchMentorsService.createSmallMentorDTO(mentList);
        } else {
            return new ResponseEntity<List<SmallDataMentorDTO>>(HttpStatus.NOT_FOUND);
        }
    }*/

}

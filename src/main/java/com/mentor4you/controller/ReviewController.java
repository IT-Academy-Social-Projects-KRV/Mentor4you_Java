package com.mentor4you.controller;

import com.mentor4you.model.Review;
import com.mentor4you.repository.ReviewRepository;
import com.mentor4you.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ReviewRepository reviewRepository;


    @PostMapping("addTest")
    public ResponseEntity<Review> testAdd(){
        return reviewService.addReview();
    }
    @GetMapping("getTest")
    public ResponseEntity<List<Review>> testGet(){
        List<Review> s = new ArrayList<>();
        s = reviewRepository.findAll();
        return new ResponseEntity<List<Review>>(s, HttpStatus.OK);
    }


}

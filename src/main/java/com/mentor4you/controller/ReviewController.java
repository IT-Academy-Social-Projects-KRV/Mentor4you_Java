package com.mentor4you.controller;


import com.mentor4you.model.DTO.review.CreateReviewDTO;
import com.mentor4you.model.Review;
import com.mentor4you.repository.ReviewRepository;
import com.mentor4you.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ReviewRepository reviewRepository;


    @PostMapping("addReview/{id}")
    public ResponseEntity<String> addReview(@PathVariable("id")int id, @RequestBody CreateReviewDTO dto, HttpServletRequest request){
        return reviewService.addReview(id,dto,request);
    }
    @GetMapping("getTest")
    public ResponseEntity<List<Review>> testGet(){
        List<Review> s = new ArrayList<>();
        s = reviewRepository.findAll();
        return new ResponseEntity<List<Review>>(s, HttpStatus.OK);
    }


}

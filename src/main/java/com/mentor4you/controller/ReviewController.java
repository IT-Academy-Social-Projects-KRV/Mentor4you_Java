package com.mentor4you.controller;


import com.mentor4you.model.DTO.review.CreateReviewDTO;
import com.mentor4you.model.DTO.review.ReviewDTO;
import com.mentor4you.model.Review;
import com.mentor4you.repository.ReviewRepository;
import com.mentor4you.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;


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
    @GetMapping("getMentorReview/{id}")
    public ResponseEntity<Set<ReviewDTO>> getMentorReview(@PathVariable("id")int id){

        return reviewService.getMentorReview(id);
    }
    @PutMapping("updateReview/{id}")
    public ResponseEntity<String> updateReview(@PathVariable("id")String id,@RequestBody CreateReviewDTO dto, HttpServletRequest request){

        return reviewService.updateReview(id,dto,request);
    }
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MODERATOR')")
    @PutMapping("hide/{id}")
    public ResponseEntity<String> hideReview(@PathVariable("id")String id){

        return reviewService.hideReview(id);
    }


}

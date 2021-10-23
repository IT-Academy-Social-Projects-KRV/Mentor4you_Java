package com.mentor4you.controller;


import com.mentor4you.exception.CooperationNotFoundException;
import com.mentor4you.exception.UserNotFoundException;
import com.mentor4you.model.DTO.review.CreateReviewDTO;
import com.mentor4you.model.DTO.review.ResponseMentorDTO;
import com.mentor4you.model.DTO.review.ReviewDTO;
import com.mentor4you.model.Review;
import com.mentor4you.repository.ReviewRepository;
import com.mentor4you.security.jwt.JwtProvider;
import com.mentor4you.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    private JwtProvider jwtProvider;

    @PostMapping("addReview/{id}")
    public ResponseEntity<String> addReview(@PathVariable("id")int id, @RequestBody CreateReviewDTO dto, HttpServletRequest request){
        try {
            reviewService.addReview(id,dto,emailFromToken(request));
            return new ResponseEntity<String>(HttpStatus.OK);
        }catch (UserNotFoundException e){
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
        }catch (CooperationNotFoundException e){
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.LOCKED);
        }catch (RuntimeException e){
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.LOCKED);
        }
    }
    @GetMapping("getMentorReview/{id}")
    public ResponseEntity<?> getMentorReview(@PathVariable("id")int id){
        try {
           return ResponseEntity.ok(reviewService.getMentorReview(id));
        }catch (UserNotFoundException e){
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("updateReview/{id}")
    public ResponseEntity<String> updateReview(@PathVariable("id")String id,@RequestBody CreateReviewDTO dto, HttpServletRequest request){

        try{
            reviewService.updateReview(id,dto,emailFromToken(request));
            return new ResponseEntity<String>(HttpStatus.OK);
        }catch (UserNotFoundException e){
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
        }catch (NullPointerException e){
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
        }catch (RuntimeException e){
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.LOCKED);
        }
    }
    @PutMapping("responseReview")
    public ResponseEntity<String> responseReview(@RequestBody ResponseMentorDTO dto, HttpServletRequest request){

        try{
            reviewService.responseReview(dto,emailFromToken(request));
            return new ResponseEntity<String>(HttpStatus.OK);
        }catch (UserNotFoundException e){
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
        }catch (NullPointerException e){
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
        }catch (RuntimeException e){
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.LOCKED);
        }
    }
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MODERATOR')")
    @PutMapping("hide/{id}")
    public ResponseEntity<String> hideReview(@PathVariable("id")String id){

        try {
            reviewService.hideReview(id);
            return new ResponseEntity<String>(HttpStatus.OK);

        }catch (NullPointerException e){
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
    private String emailFromToken(HttpServletRequest request){
        String token =jwtProvider.getTokenFromRequest(request);

        String email =jwtProvider.getLoginFromToken(token);

        return email;
    }

}

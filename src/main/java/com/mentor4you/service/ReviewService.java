package com.mentor4you.service;

import com.mentor4you.model.Review;
import com.mentor4you.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public ResponseEntity<Review> addReview(){
        Review review =new Review();
        review.setMentorId(1);
        review.setMessage("sdfsdf");
        review.setSenderId(1);
        review.setShowStatus(true);
        review.setRating(5.0);
        reviewRepository.save(review);

        return new ResponseEntity<Review>(review, HttpStatus.CREATED);
    }
}

package com.mentor4you.service;

import com.mentor4you.model.DTO.review.CreateReviewDTO;
import com.mentor4you.model.Review;
import com.mentor4you.model.Role;
import com.mentor4you.model.User;
import com.mentor4you.repository.ReviewRepository;
import com.mentor4you.repository.UserRepository;
import com.mentor4you.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Service
public class ReviewService {

    @Autowired
    private CooperationService cooperationService;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtProvider jwtProvider;

    public ResponseEntity<String> addReview(int id, CreateReviewDTO reviewDTO,HttpServletRequest request){
        String token =jwtProvider.getTokenFromRequest(request);
        String email =jwtProvider.getLoginFromToken(token);

        User user = userRepository.findUserByEmail(email);
        if(user != null && user.getRole() == Role.MENTEE){

        Review review =new Review();

        if(userRepository.findOneById(id)==null)
            return new ResponseEntity<String>("mentor does not exist",HttpStatus.NOT_FOUND);

        if(reviewRepository.existsByUsers(user.getId(),id).isEmpty()==false&&cooperationService.checkInfo(user.getId(), id))
            return new ResponseEntity<String>("review already exist",HttpStatus.LOCKED);

        review.setMentorId(id);

        System.out.println(reviewDTO);

        review.setMessage(reviewDTO.getMessage());

        review.setSenderId(user.getId());

        review.setShowStatus(true);

        review.setRating(reviewDTO.getRating());

        review.setTimestamp(LocalDateTime.now()) ;


        reviewRepository.save(review);

        return new ResponseEntity<String>(HttpStatus.OK);
        }
        return new ResponseEntity<String>("user not found or user role not mentee",HttpStatus.BAD_REQUEST);
    }
}

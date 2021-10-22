package com.mentor4you.service;

import com.mentor4you.exception.CooperationNotFoundException;
import com.mentor4you.exception.UserNotFoundException;
import com.mentor4you.model.DTO.MinUserDTO;
import com.mentor4you.model.DTO.review.CreateReviewDTO;
import com.mentor4you.model.DTO.review.ResponseMentorDTO;
import com.mentor4you.model.DTO.review.ReviewDTO;
import com.mentor4you.model.Review;
import com.mentor4you.model.Role;
import com.mentor4you.model.User;
import com.mentor4you.repository.MentorRepository;
import com.mentor4you.repository.ReviewRepository;
import com.mentor4you.repository.UserRepository;
import com.mentor4you.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ReviewService {

    @Autowired
    private CooperationService cooperationService;

    @Autowired
    private MentorRepository mentorRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtProvider jwtProvider;

    public Boolean addReview(int id, CreateReviewDTO reviewDTO, String email){
        User user = userRepository.findUserByEmail(email);
        if(user != null && user.getRole() == Role.MENTEE){

        Review review =new Review();

        if(userRepository.findOneById(id)==null)
            throw  new UserNotFoundException("mentor does not exist");
        if (!cooperationService.checkInfo(user.getId(), id))
            throw new CooperationNotFoundException("Cooperation don`t started");

        if(!reviewRepository.existsByUsers(user.getId(),id).isEmpty())
            throw  new RuntimeException("review already exist");

        review.setMentorId(id);

        review.setMessage(reviewDTO.getMessage());

        review.setSenderId(user.getId());

        review.setShowStatus(true);

        review.setRating(reviewDTO.getRating());

        review.setTimestamp(LocalDateTime.now()) ;

        reviewRepository.save(review);

        avg(id);

        return true;
        }
        throw new RuntimeException("user not found or user role not mentee");
    }

    public Set<ReviewDTO> getMentorReview(int id){
        User user =userRepository.findOneById(id);
        if(user == null){
            throw  new UserNotFoundException("mentor does not exist");
        }


        Set<Review> s =new HashSet<>();
        s.addAll(reviewRepository.reviewByMentor(id));
        Set<ReviewDTO> dto =new HashSet<>();
        for(Review r :s){
            user=userRepository.findOneById(r.getSenderId());
            if(user != null){
                MinUserDTO usr= new MinUserDTO(user.getId(),user.getFirst_name(),user.getLast_name(),user.getAvatar());
                dto.add(new ReviewDTO(usr,r));
            }
        }
        return dto;

    }
    public Boolean updateReview(String id, CreateReviewDTO reviewDTO, String email){

        User user = userRepository.findUserByEmail(email);
        if(user != null){

            Review review = reviewRepository.reviewById(id);

            if(review==null)
                throw  new NullPointerException("Review does not exist");
            if(user.getId()!=review.getSenderId())
                throw  new RuntimeException("This user does not have permission to update");


            review.setMessage(reviewDTO.getMessage());

            review.setShowStatus(true);

            review.setRating(reviewDTO.getRating());

            review.setTimestamp(LocalDateTime.now()) ;

            reviewRepository.save(review);

            avg(review.getMentorId());

            return true;
        }

        throw  new UserNotFoundException("user not found");
    }


    public Boolean hideReview(String id){
        Review review=reviewRepository.reviewById(id);
        if(review ==null)
            throw  new NullPointerException("review not found");
        if(!review.isShowStatus())
            return true;
        review.setShowStatus(false);
        reviewRepository.save(review);
        return true;

    }
    public Boolean responseReview(ResponseMentorDTO dto, String email){

        User user = userRepository.findUserByEmail(email);
        if(user != null){

            Review review = reviewRepository.reviewById(dto.getId());

            if(review==null)
                throw  new NullPointerException("Review does not exist");
            if(user.getId()!=review.getMentorId())
                throw  new RuntimeException("This user does not have permission to update");

            review.setResponse(dto.getResponse());

            reviewRepository.save(review);

            return true;
        }
        throw  new UserNotFoundException("user not found");
    }

    private void avg(int mentor){
        List<Review> reviews = reviewRepository.reviewByMentor(mentor);
        double result =reviews.stream().mapToDouble(o->o.getRating()).average().getAsDouble();
        result=Math.round(result * 100.0) / 100.0;
        mentorRepository.updateRating(result,mentor);
    }
}

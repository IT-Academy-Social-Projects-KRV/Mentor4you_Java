package com.mentor4you.service;

import com.mentor4you.model.DTO.MinUserDTO;
import com.mentor4you.model.DTO.review.CreateReviewDTO;
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

    public ResponseEntity<String> addReview(int id, CreateReviewDTO reviewDTO, HttpServletRequest request){
        String token =jwtProvider.getTokenFromRequest(request);
        String email =jwtProvider.getLoginFromToken(token);

        User user = userRepository.findUserByEmail(email);
        if(user != null && user.getRole() == Role.MENTEE){

        Review review =new Review();

        if(userRepository.findOneById(id)==null)
            return new ResponseEntity<String>("mentor does not exist",HttpStatus.NOT_FOUND);
        if (!cooperationService.checkInfo(user.getId(), id))
            return new ResponseEntity<String>("Cooperation don`t started",HttpStatus.LOCKED);

        if(!reviewRepository.existsByUsers(user.getId(),id).isEmpty())
            return new ResponseEntity<String>("review already exist",HttpStatus.LOCKED);

        review.setMentorId(id);

        review.setMessage(reviewDTO.getMessage());

        review.setSenderId(user.getId());

        review.setShowStatus(true);

        review.setRating(reviewDTO.getRating());

        review.setTimestamp(LocalDateTime.now()) ;

        reviewRepository.save(review);

        avg(id);

        return new ResponseEntity<String>(HttpStatus.OK);
        }
        return new ResponseEntity<String>("user not found or user role not mentee",HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Set<ReviewDTO>> getMentorReview(int id){
        User user =userRepository.findOneById(id);
        if(user == null){
            return new ResponseEntity<Set<ReviewDTO>>(HttpStatus.NOT_FOUND);
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
        return new ResponseEntity<Set<ReviewDTO>>(dto,HttpStatus.OK);

    }
    public ResponseEntity<String> updateReview(String id, CreateReviewDTO reviewDTO, HttpServletRequest request){
        String token =jwtProvider.getTokenFromRequest(request);
        String email =jwtProvider.getLoginFromToken(token);

        User user = userRepository.findUserByEmail(email);
        if(user != null){

            Review review = reviewRepository.reviewById(id);

            if(review==null)
                return new ResponseEntity<String>("Review does not exist",HttpStatus.NOT_FOUND);
            if(user.getId()!=review.getSenderId())
                return new ResponseEntity<String>("This user does not have permission to update",HttpStatus.LOCKED);


            review.setMessage(reviewDTO.getMessage());

            review.setShowStatus(true);

            review.setRating(reviewDTO.getRating());

            review.setTimestamp(LocalDateTime.now()) ;

            reviewRepository.save(review);


            return new ResponseEntity<String>(HttpStatus.OK);
        }
        return new ResponseEntity<String>("user not found",HttpStatus.BAD_REQUEST);
    }

    private void avg(int mentor){
        List<Review> reviews = reviewRepository.reviewByMentor(mentor);
        double result =reviews.stream().filter(o -> o.getRating()>=0).mapToDouble(o->o.getRating()).average().getAsDouble();
        mentorRepository.updateRating(mentor,result);
        System.out.println(result);
    }

}

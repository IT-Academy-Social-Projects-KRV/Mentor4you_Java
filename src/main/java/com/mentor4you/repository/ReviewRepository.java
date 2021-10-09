package com.mentor4you.repository;

import com.mentor4you.model.Review;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;


public interface ReviewRepository extends MongoRepository<Review,Integer>{

 @Query("{ 'senderId' : ?0 },{ 'mentorId':?1 }")
 List<Review> existsByUsers(int sender, int mentor);

}

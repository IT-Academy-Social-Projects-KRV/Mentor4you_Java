package com.mentor4you.repository;

import com.mentor4you.model.Review;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;


public interface ReviewRepository extends MongoRepository<Review,Integer>{

 @Query("{ 'senderId' : ?0 },{ 'mentorId':?1 }")
 List<Review> existsByUsers(int sender, int mentor);

 @Query("{'mentorId':?0 ,showStatus: true}")
 List<Review> reviewByMentor(int mentor);

 @Query("{ '_id':?0 }")
 Review reviewById(String id);

 @Aggregation(pipeline = { " " +
         "{ $match: { mentorId: { $eq: ?0} } }," +
         "{$group : {rating_average : {$avg : '$rating'}}}" })
 double avg(int mentor);

}

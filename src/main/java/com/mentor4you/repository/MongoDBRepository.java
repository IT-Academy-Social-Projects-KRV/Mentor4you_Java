package com.mentor4you.repository;

import com.mentor4you.model.Mongo.Mongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoDBRepository extends MongoRepository<Mongo, Integer> {
}

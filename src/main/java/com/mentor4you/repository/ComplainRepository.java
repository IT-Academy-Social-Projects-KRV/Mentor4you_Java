package com.mentor4you.repository;

import com.mentor4you.model.Complaint;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComplainRepository extends MongoRepository<Complaint, String> {



}

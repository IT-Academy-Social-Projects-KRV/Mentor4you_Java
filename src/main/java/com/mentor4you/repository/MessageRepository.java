package com.mentor4you.repository;

import com.mentor4you.model.chat.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessageRepository extends MongoRepository<ChatMessage, String> {

   }

package com.mentor4you.repository;

import com.mentor4you.model.ChatMessage;
import com.mentor4you.model.ChatRoom;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {

    List<ChatMessage> findAllByChatId(String toString);
}

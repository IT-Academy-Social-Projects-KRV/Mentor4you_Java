package com.mentor4you.repository;

import com.mentor4you.model.ChatRoom;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ChatRoomRepository extends MongoRepository<ChatRoom, String> {
    Optional<ChatRoom> findBySenderIdAndRecipientId(String s, String id);

    List<ChatRoom> findAllBySenderId(String toString);
}

package com.mentor4you.repository;

import com.mentor4you.model.ChatMessage;
import com.mentor4you.model.ChatRoom;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRoomRepository extends MongoRepository<ChatRoom, String> {
    Optional<ChatRoom> findBySenderIdAndRecipientId(String s, String id);

    List<ChatRoom> findAllBySenderId(String toString);

    List<ChatRoom> findAllByRecipientId(String toString);

    List<ChatRoom> findAllBySenderIdAndRecipientId(String tostring, String tostring2);

    void insert(ChatMessage message);
}

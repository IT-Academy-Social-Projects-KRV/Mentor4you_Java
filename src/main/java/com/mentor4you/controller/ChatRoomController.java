package com.mentor4you.controller;

import com.mentor4you.model.ChatMessage;
import com.mentor4you.model.ChatRoom;
import com.mentor4you.repository.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/chat")
public class ChatRoomController {

    @Autowired
    private ChatRoomRepository chatRoomRepository;
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/{to}")
    public void sendMessage(@DestinationVariable String to, ChatMessage message) {
        message.setTimestamp(LocalDateTime.now());
        simpMessagingTemplate.convertAndSend("/topic/messages/" + to, message);
    }

    //get all chat
    @GetMapping
    ResponseEntity<?> getAllRoom(){
        return ResponseEntity.ok(chatRoomRepository.findAll());
    }

//    //add new Chat room
//        @PostMapping("/{id}")
//    ResponseEntity<?> createRoom(@PathVariable String id) {
//            String senderId = "1";
//            Optional<ChatRoom> roomOptional = chatRoomRepository.findBySenderIdAndRecipientId(senderId, id);
//            Optional<ChatRoom> reverseRoomOptional = chatRoomRepository.findBySenderIdAndRecipientId(id, senderId);
//            if (roomOptional.isEmpty() | reverseRoomOptional.isEmpty()) {
//                String chatId = String.format("%s_%s", senderId, id);
//                ChatRoom room = new ChatRoom(chatId, senderId, id ,"name","Avatar_url");
//                return ResponseEntity.status(201).body(chatRoomRepository.save(room));
//            }
//            return ResponseEntity.status(201).body(reverseRoomOptional);
//        }
}

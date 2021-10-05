package com.mentor4you.controller;

import com.mentor4you.model.ChatMessage;
import com.mentor4you.model.ChatRoom;
import com.mentor4you.repository.ChatRoomRepository;
import com.mentor4you.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/chat")
public class ChatRoomController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    ChatRoomRepository chatRoomRepository;
    ChatService chatService;

    public ChatRoomController(ChatRoomRepository chatRoomRepository, SimpMessagingTemplate simpMessagingTemplate, ChatService chatService) {
        this.chatRoomRepository = chatRoomRepository;
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.chatService = chatService;
    }

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

    //get or add new Chat room
    @PostMapping("/{id}")
    ResponseEntity<?> getRoom(HttpServletRequest request, @PathVariable String id) {
//        try {
//            chatService.getRoom(request,id);
//            return ResponseEntity.ok("me");
//        }catch (NullPointerException e){
            return ResponseEntity.ok(chatService.createRoom(request, id));
//        }

        }
}

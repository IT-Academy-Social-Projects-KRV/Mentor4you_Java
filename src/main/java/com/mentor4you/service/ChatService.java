package com.mentor4you.service;

import com.mentor4you.exception.ChatNotFoundException;
import com.mentor4you.model.ChatMessage;
import com.mentor4you.model.ChatRoom;
import com.mentor4you.model.User;
import com.mentor4you.repository.ChatMessageRepository;
import com.mentor4you.repository.ChatRoomRepository;
import com.mentor4you.repository.UserRepository;
import com.mentor4you.security.jwt.JwtProvider;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Service
public class ChatService {



    @Autowired
    JwtProvider jwtProvider;
    UserRepository userRepository;
    ChatRoomRepository chatRoomRepository;
    ChatMessageRepository chatMessageRepository;

    public ChatService(JwtProvider jwtProvider, UserRepository userRepository, ChatRoomRepository chatRoomRepository, ChatMessageRepository chatMessageRepository) {
        this.jwtProvider = jwtProvider;
        this.userRepository = userRepository;
        this.chatRoomRepository = chatRoomRepository;
        this.chatMessageRepository = chatMessageRepository;
    }


    public Optional<ChatRoom> getRoom(HttpServletRequest request, String id) {

        String token = jwtProvider.getTokenFromRequest(request);
        String email = jwtProvider.getLoginFromToken(token);
        User user = userRepository.findUserByEmail(email);

        String userId = Integer.toString(user.getId());

        Optional<ChatRoom> roomOptional = chatRoomRepository.findBySenderIdAndRecipientId(userId, id);
        if (roomOptional.isEmpty()) {
            throw new ChatNotFoundException("Chat not found");
        }
        return roomOptional;
    }

    public ChatRoom createRoom(HttpServletRequest request, String id) {

        String token = jwtProvider.getTokenFromRequest(request);
        String email = jwtProvider.getLoginFromToken(token);
        User user = userRepository.findUserByEmail(email);
        String userId = Integer.toString(user.getId());

        User recipient = userRepository.findOneById(Integer.parseInt(id));

        String chatId = String.format("%s_%s", userId, id);
        ChatRoom room = new ChatRoom(chatId, userId, id);
        chatRoomRepository.save(room);
        String chatId1 = String.format("%s_%s", id, userId);
        ChatRoom room1 = new ChatRoom(chatId1, id, userId);
        chatRoomRepository.save(room1);
        return room;
    }

    public List<ChatRoom> findMyRoom(HttpServletRequest request) {
        String token = jwtProvider.getTokenFromRequest(request);
        String email = jwtProvider.getLoginFromToken(token);
        User user = userRepository.findUserByEmail(email);

        List<ChatRoom> roomList = chatRoomRepository.findAllBySenderId(Integer.toString(user.getId()));
        return roomList;
    }

    public ResponseEntity<List<ChatMessage>> findMessage(String sendid, String recivid) {
        List<ChatRoom> messages = chatRoomRepository.findAllBySenderIdAndRecipientId(sendid,recivid);
        if (messages.size() > 0) {
            messages.get(0).getChatId();
            List<ChatMessage> chats = chatMessageRepository.findAllByChatId( messages.get(0).getChatId());

            return new ResponseEntity<List<ChatMessage>>(chats,HttpStatus.OK);
        } else {
            return new ResponseEntity<List<ChatMessage>>(HttpStatus.NOT_FOUND);
        }
    }


}

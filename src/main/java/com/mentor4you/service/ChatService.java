package com.mentor4you.service;

import com.mentor4you.exception.ChatNotFoundException;
import com.mentor4you.model.ChatRoom;
import com.mentor4you.model.User;
import com.mentor4you.repository.ChatRoomRepository;
import com.mentor4you.repository.UserRepository;
import com.mentor4you.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Service
public class ChatService {

    @Autowired
    JwtProvider jwtProvider;
    UserRepository userRepository;
    ChatRoomRepository chatRoomRepository;

    public ChatService(JwtProvider jwtProvider, UserRepository userRepository, ChatRoomRepository chatRoomRepository) {
        this.jwtProvider = jwtProvider;
        this.userRepository = userRepository;
        this.chatRoomRepository = chatRoomRepository;
    }


    public Optional<ChatRoom> getRoom(HttpServletRequest request, String id) {

      //  String token = jwtProvider.getTokenFromRequest(request);
     //   String email = jwtProvider.getLoginFromToken(token);
        User user = userRepository.findUserByEmail("email");

        String userId = Integer.toString(user.getId());

        Optional<ChatRoom> roomOptional = chatRoomRepository.findBySenderIdAndRecipientId(userId, id);
        if(roomOptional.isEmpty()){
            throw new ChatNotFoundException("Chat not found");
        }
            return roomOptional;
    }

    public ChatRoom createRoom(HttpServletRequest request, String id) {

      //  String token = jwtProvider.getTokenFromRequest(request);
     //   String email = jwtProvider.getLoginFromToken(token);
        User user = userRepository.findUserByEmail("email");
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
     //   String token = jwtProvider.getTokenFromRequest(request);
     //   String email = jwtProvider.getLoginFromToken(token);
        User user = userRepository.findUserByEmail("email");

        List<ChatRoom> roomList = chatRoomRepository.findAllBySenderId(Integer.toString(user.getId()));
        return roomList;
    }



}

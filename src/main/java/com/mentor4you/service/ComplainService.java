package com.mentor4you.service;

import com.mentor4you.model.DTO.ComplainDTO.ComplainResponseDTO;
import com.mentor4you.model.DTO.mentorsExtendedInfo.MentorGeneralResponseDTO;
import com.mentor4you.repository.ComplainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Service
public class ComplainService {

    @Autowired
    ComplainRepository complainRepository;


    public ComplainService(ComplainRepository complainRepository) {
        this.complainRepository = complainRepository;
    }


    public void insertNewComplain(ComplainResponseDTO dto,
                                  HttpServletRequest request) {

        String token = jwtProvider.getTokenFromRequest(request);

        String email = jwtProvider.getLoginFromToken(token);
        complainRepository.insert(message);
    }

    @MessageMapping("/complaint/{to}")
    public void sendMessage(@DestinationVariable String to, ChatMessage message) {
        message.setTimestamp(LocalDateTime.now());
        simpMessagingTemplate.convertAndSend("/topic/messages/" + to, message);
        .insert(message);
    }
}

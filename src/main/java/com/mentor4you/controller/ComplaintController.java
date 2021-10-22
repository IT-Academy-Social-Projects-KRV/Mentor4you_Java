package com.mentor4you.controller;

import com.mentor4you.model.DTO.mentorsExtendedInfo.MentorGeneralResponseDTO;
import com.mentor4you.repository.ComplainRepository;
import com.mentor4you.service.ComplainService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@RestController
public class ComplaintController {

    @Autowired
    ComplainRepository complainRepository;
    ComplainService complainService;

    public ComplaintController(ComplainRepository complainRepository,
                               ComplainService complainService) {
        this.complainRepository = complainRepository;
        this.complainService = complainService;
    }

   /* @Operation(summary = "send complaint by token")
    @PutMapping("/sendComplain/")
    ResponseEntity<String> sendComplain
            (HttpServletRequest req, @RequestBody MentorGeneralResponseDTO dto) {
        return complainService.updateMentorByToken(dto,req);
    }*/


    //Создать жалобу

    //Принимает токен  менти и из реквеста собирает данные и отправляет в базу текст, тип, дату, статус жалобы

    //Получить непросмотренные жалобы

    //Прочитать жалобу

    //Поменять статус прочитанной жалобы


}

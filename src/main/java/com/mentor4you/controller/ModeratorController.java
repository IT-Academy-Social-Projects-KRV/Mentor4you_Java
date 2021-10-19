package com.mentor4you.controller;

import com.mentor4you.model.DTO.ModeratorDTO;
import com.mentor4you.model.DTO.mentorsExtendedInfo.MentorGeneralResponseDTO;
import com.mentor4you.service.ModeratorService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

public class ModeratorController {
    @Autowired
    ModeratorService moderatorService;

    public ModeratorController(ModeratorService moderatorService) {
        this.moderatorService = moderatorService;
    }

    @Operation(summary = "select moderator by token")
    @GetMapping("/getModeratorDTO/")
    ResponseEntity<ModeratorDTO> getOneMentorByToken
            (HttpServletRequest req) {
        return moderatorService.getModeratorByToken(req);
    }
}

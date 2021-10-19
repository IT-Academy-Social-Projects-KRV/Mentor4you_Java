package com.mentor4you.service;

import com.mentor4you.exception.MenteeNotFoundException;
import com.mentor4you.exception.UserNotFoundException;
import com.mentor4you.model.*;
import com.mentor4you.model.DTO.MenteeResponseDTO;
import com.mentor4you.model.DTO.ModeratorDTO;
import com.mentor4you.model.DTO.mentorsExtendedInfo.MentorGeneralResponseDTO;
import com.mentor4you.repository.*;
import com.mentor4you.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

public class ModeratorService {
    @Autowired
    CityToMentorRepository cityToMentorRepository;
    MentorsToCategory mentorsToCategory;
    AccountRepository accountRepository;
    LanguagesService languagesService;
    MentorRepository mentorRepository;
    UserRepository userRepository;
    MenteeService menteeService;
    UserService userService;
    JwtProvider jwtProvider;

    public ModeratorService(CityToMentorRepository cityToMentorRepository, MentorsToCategory mentorsToCategory, AccountRepository accountRepository, LanguagesService languagesService, MentorRepository mentorRepository, UserRepository userRepository, MenteeService menteeService, UserService userService, JwtProvider jwtProvider) {
        this.cityToMentorRepository = cityToMentorRepository;
        this.mentorsToCategory = mentorsToCategory;
        this.accountRepository = accountRepository;
        this.languagesService = languagesService;
        this.mentorRepository = mentorRepository;
        this.userRepository = userRepository;
        this.menteeService = menteeService;
        this.userService = userService;
        this.jwtProvider = jwtProvider;
    }

    public ModeratorDTO getModeratorByToken(HttpServletRequest request){
        String token = jwtProvider.getTokenFromRequest(request);
        String email = jwtProvider.getLoginFromToken(token);
        User user = userRepository.findUserByEmail(email);

        if(user!=null){
            ModeratorDTO dto =new ModeratorDTO();
            dto.setId(user.getId());
            dto.setAvatar(user.getAvatar());
            dto.setEmail(user.getEmail());
            dto.setFirstName(user.getFirst_name());
            dto.setLastName(user.getLast_name());

            return new ModeratorDTO;
        }
        else{
            return null;}
    }
}

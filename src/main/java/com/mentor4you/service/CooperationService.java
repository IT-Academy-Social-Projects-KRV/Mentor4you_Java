package com.mentor4you.service;


import com.mentor4you.model.*;
import com.mentor4you.model.DTO.MenteeDTOforCop;
import com.mentor4you.repository.CooperationRepository;
import com.mentor4you.repository.MenteeRepository;
import com.mentor4you.repository.MentorRepository;
import com.mentor4you.repository.UserRepository;
import com.mentor4you.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

import java.util.HashSet;
import java.util.Set;

@Transactional
@Service
public class CooperationService {
    @Autowired
    CooperationRepository cooperationRepository;
    MentorRepository mentorRepository;
    MenteeRepository menteeRepository;
    UserRepository userRepository;
    JwtProvider jwtProvider;

    public CooperationService(CooperationRepository cooperationRepository,
                              MentorRepository mentorRepository,
                              MenteeRepository menteeRepository,
                              UserRepository userRepository,
                              JwtProvider jwtProvider) {
        this.cooperationRepository = cooperationRepository;
        this.mentorRepository = mentorRepository;
        this.menteeRepository = menteeRepository;
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
    }

    public ResponseEntity<String> createCooperation(int id, HttpServletRequest request){
        String token =jwtProvider.getTokenFromRequest(request);

        String email =jwtProvider.getLoginFromToken(token);

        Mentors mentor =mentorRepository.getById(id);
        Mentees mentee =menteeRepository.getById(userRepository.findUserByEmail(email).getId());

        if(cooperationRepository.coopIsPresent(mentee,mentor)!=null){
            return new ResponseEntity<String>("Cooperation already exists",HttpStatus.BAD_REQUEST);
        }

        Cooperation c =new Cooperation();
        c.setMentors(mentor);
        c.setMentees(mentee);
        c.setStatus(0);
        cooperationRepository.save(c);
        return new ResponseEntity<String>(HttpStatus.OK);
    }


    public ResponseEntity<Set<MenteeDTOforCop>> getCooperation(HttpServletRequest request){
        String token =jwtProvider.getTokenFromRequest(request);

        String email =jwtProvider.getLoginFromToken(token);

        User user = userRepository.findUserByEmail(email);


        Set<MenteeDTOforCop> s =new HashSet<>();


        for(Cooperation c :cooperationRepository.findByMentors(mentorRepository.getById(user.getId()))){
            MenteeDTOforCop dto = new MenteeDTOforCop();
            dto.setId(c.getMentees().getId());
            dto.setName(c.getMentees().getAccounts().getUser().getFirst_name());
            dto.setSecondName(c.getMentees().getAccounts().getUser().getLast_name());
            s.add(dto);
        }

        if(s.isEmpty()==false)
            return new ResponseEntity<Set<MenteeDTOforCop>>(s,HttpStatus.OK);
        else return new ResponseEntity<Set<MenteeDTOforCop>>(HttpStatus.NOT_FOUND);
    }



    public ResponseEntity<String> approveCooperation(HttpServletRequest request,int id){
        String token =jwtProvider.getTokenFromRequest(request);
        String email =jwtProvider.getLoginFromToken(token);
        Mentors mentor =mentorRepository.getById(userRepository.findUserByEmail(email).getId());
        Mentees mentee =menteeRepository.getById(id);
        Cooperation cooperation = cooperationRepository.coopIsPresent(mentee,mentor);
        System.out.println(cooperation);
        if(cooperationRepository.coopIsPresent(mentee,mentor)!=null){

            cooperation.setStatus(1);
            cooperationRepository.save(cooperation);
            return new ResponseEntity<String>("cooperation was started",HttpStatus.OK);
        }
        else  return new ResponseEntity<String>("fail",HttpStatus.LOCKED);
        //cooperationRepository.approve(mentee,mentor,1);


    }
}

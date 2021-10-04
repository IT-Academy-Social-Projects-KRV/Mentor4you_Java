package com.mentor4you.service;


import com.mentor4you.model.*;
import com.mentor4you.model.DTO.DTOforCop;
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

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
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


        Mentors mentor=null;
        try{
            mentor =mentorRepository.getById(id);
        }catch (EntityNotFoundException e){
            return new ResponseEntity<String>("Mentor not found",HttpStatus.NOT_FOUND);
        }
        Mentees mentee =menteeRepository.getById(userRepository.findUserByEmail(email).getId());
        Cooperation c =cooperationRepository.coopIsPresent(mentee.getId(),mentor.getId());

            try {
                if(c.getStatus() == CoopStatus.CREATED || c.getStatus() == CoopStatus.APPROVED)
                    return new ResponseEntity<String>("Cooperation already exists",HttpStatus.BAD_REQUEST);
            }
            catch(NullPointerException exception){
                c =new Cooperation();
                c.setMentors(mentor);
                c.setMentees(mentee);
            }
            finally {
                    c.setStatus(CoopStatus.CREATED);
                    cooperationRepository.save(c);
            }
        return new ResponseEntity<String>("Cooperation Request created",HttpStatus.OK);
    }


    public ResponseEntity<Set<DTOforCop>>getCooperationForMentor(HttpServletRequest request){
        String token =jwtProvider.getTokenFromRequest(request);

        String email =jwtProvider.getLoginFromToken(token);

        User user = userRepository.findUserByEmail(email);

        Set<DTOforCop> s =new HashSet<>();

        for(Cooperation c :cooperationRepository.findByMentors(user.getId(),CoopStatus.CREATED)){
            DTOforCop dto = new DTOforCop();
            dto.setId(c.getMentees().getId());
            dto.setName(c.getMentees().getAccounts().getUser().getFirst_name());
            dto.setSecondName(c.getMentees().getAccounts().getUser().getLast_name());
            s.add(dto);
        }
        if(s.isEmpty()==false)
            return new ResponseEntity<Set<DTOforCop>>(s,HttpStatus.OK);
        else return new ResponseEntity<Set<DTOforCop>>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Set<DTOforCop>> getCooperationForMentee(HttpServletRequest request){
        String token =jwtProvider.getTokenFromRequest(request);

        String email =jwtProvider.getLoginFromToken(token);

        User user = userRepository.findUserByEmail(email);

        Set<DTOforCop> s =new HashSet<>();

        Set<CoopStatus> coop = new HashSet<>();
        coop.add(CoopStatus.APPROVED);
        System.out.println(coop);
        System.out.println(cooperationRepository.findByMentees(user.getId(),coop));

        for(Cooperation c :cooperationRepository.findByMentees(user.getId(),coop)){
            DTOforCop dto = new DTOforCop();
            dto.setId(c.getMentors().getId());
            dto.setName(c.getMentors().getAccounts().getUser().getFirst_name());
            dto.setSecondName(c.getMentors().getAccounts().getUser().getLast_name());
            s.add(dto);
        }
        if(s.isEmpty()==false)
            return new ResponseEntity<Set<DTOforCop>>(s,HttpStatus.OK);
        else return new ResponseEntity<Set<DTOforCop>>(HttpStatus.NOT_FOUND);
    }



    public ResponseEntity<String> decisionsOnCoop(HttpServletRequest request,int id,Boolean s){
        String token =jwtProvider.getTokenFromRequest(request);
        String email =jwtProvider.getLoginFromToken(token);
        Mentors mentor =mentorRepository.getById(userRepository.findUserByEmail(email).getId());
        Mentees mentee =menteeRepository.getById(id);
        Cooperation cooperation = cooperationRepository.coopIsPresent(mentee.getId(),mentor.getId());
        String massage = null;
        if(cooperation!=null){

            if(s){
                cooperation.setStatus(CoopStatus.APPROVED);
                massage ="Coop start";
            }
            else {
                cooperation.setStatus(CoopStatus.REJECTED);
                massage ="Coop rejected";

            }
            cooperationRepository.save(cooperation);
            return new ResponseEntity<String>(massage,HttpStatus.OK);

        }
        else  return new ResponseEntity<String>("fail",HttpStatus.NOT_FOUND);
        //cooperationRepository.approve(mentee,mentor,1);


    }
}

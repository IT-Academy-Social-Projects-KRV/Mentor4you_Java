package com.mentor4you.service;


import com.mentor4you.model.*;
import com.mentor4you.model.DTO.coopDTO.CoopStatus;
import com.mentor4you.model.DTO.coopDTO.DTOforCopUser;
import com.mentor4you.model.DTO.coopDTO.DTOstatusCoopMentee;
import com.mentor4you.model.DTO.coopDTO.StatusBoolDTO;
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



        Mentors  mentor =mentorRepository.getById(id);

        Mentees mentee =menteeRepository.getById(userRepository.findUserByEmail(email).getId());
        Cooperation c =cooperationRepository.coopIsPresent(mentee.getId(),mentor.getId());

            try {
                if(c.getStatus() != CoopStatus.REJECTED)
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
        return new ResponseEntity<String>(HttpStatus.OK);
    }


    public ResponseEntity<Set<DTOforCopUser>>getCooperationForMentor(HttpServletRequest request){
        String token =jwtProvider.getTokenFromRequest(request);

        String email =jwtProvider.getLoginFromToken(token);

        User user = userRepository.findUserByEmail(email);

        Set<DTOforCopUser> s =new HashSet<>();

        for(Cooperation c :cooperationRepository.findByMentors(user.getId(),CoopStatus.CREATED)){
            DTOforCopUser dto = new DTOforCopUser();
            dto.setId(c.getMentees().getId());
            dto.setName(c.getMentees().getAccounts().getUser().getFirst_name());
            dto.setSecondName(c.getMentees().getAccounts().getUser().getLast_name());
            dto.setAvatar(c.getMentees().getAccounts().getUser().getAvatar());
            s.add(dto);
        }
            return new ResponseEntity<Set<DTOforCopUser>>(s,HttpStatus.OK);
    }

    public ResponseEntity<Set<DTOstatusCoopMentee>> getCooperationForMentee(HttpServletRequest request){
        String token =jwtProvider.getTokenFromRequest(request);

        String email =jwtProvider.getLoginFromToken(token);

        User user = userRepository.findUserByEmail(email);

        Set<DTOstatusCoopMentee> s =new HashSet<>();

        Set<CoopStatus> coop = new HashSet<>();
        coop.add(CoopStatus.APPROVED);
        coop.add(CoopStatus.REJECTED);

        for(Cooperation c :cooperationRepository.findByMentees(user.getId(),coop)){
            DTOforCopUser dtoUser = new DTOforCopUser();

            dtoUser.setId(c.getMentors().getId());
            dtoUser.setName(c.getMentors().getAccounts().getUser().getFirst_name());
            dtoUser.setSecondName(c.getMentors().getAccounts().getUser().getLast_name());
            dtoUser.setAvatar(c.getMentees().getAccounts().getUser().getAvatar());

            DTOstatusCoopMentee dto =new DTOstatusCoopMentee();
            dto.setMentor(dtoUser);
            dto.setCoopStatus(c.getStatus());

            s.add(dto);
        }
        return new ResponseEntity<Set<DTOstatusCoopMentee>>(s,HttpStatus.OK);

    }



    public ResponseEntity<String> decisionsOnCoop(HttpServletRequest request, int id, StatusBoolDTO s){
        String token =jwtProvider.getTokenFromRequest(request);
        String email =jwtProvider.getLoginFromToken(token);
        Mentors mentor =mentorRepository.getById(userRepository.findUserByEmail(email).getId());
        Mentees mentee =menteeRepository.getById(id);
        Cooperation cooperation = cooperationRepository.coopIsPresent(mentee.getId(),mentor.getId());
        String massage = null;
        if(cooperation!=null){

            if(s.getStatus()){
                cooperation.setStatus(CoopStatus.APPROVED);
            }
            else {
                cooperation.setStatus(CoopStatus.REJECTED);

            }
            cooperationRepository.save(cooperation);
            return new ResponseEntity<String>(HttpStatus.OK);

        }
        else  return new ResponseEntity<String>("fail",HttpStatus.NOT_FOUND);
    }
    public ResponseEntity<String> responseMentee(HttpServletRequest request,int mentorId){
        String token =jwtProvider.getTokenFromRequest(request);
        String email =jwtProvider.getLoginFromToken(token);
        Mentees mentee =menteeRepository.getById(userRepository.findUserByEmail(email).getId());
        Cooperation cooperation = cooperationRepository.coopIsPresent(mentee.getId(),mentorId);

        if(cooperation == null) return new ResponseEntity<String>("this coop does not exist",HttpStatus.NOT_FOUND);

        if(cooperation.getStatus() == CoopStatus.APPROVED)
            cooperation.setStatus(CoopStatus.STARTED);
        else if(cooperation.getStatus() == CoopStatus.REJECTED)
            cooperation.setStatus(CoopStatus.FROZEN);

        cooperationRepository.save(cooperation);
        return new ResponseEntity<String>(HttpStatus.OK);
    }
    public ResponseEntity<Boolean>showInformation(HttpServletRequest request,int mentorId){
        String token =jwtProvider.getTokenFromRequest(request);
        String email =jwtProvider.getLoginFromToken(token);
        Mentees mentee =menteeRepository.getById(userRepository.findUserByEmail(email).getId());

        return new ResponseEntity<Boolean>(checkInfo(mentee.getId(),mentorId),HttpStatus.OK);


    }
    public Boolean checkInfo(int menteeId,int mentorId){
;
        Cooperation cooperation = cooperationRepository.coopIsPresent(menteeId,mentorId);

        if(cooperation == null)  return null;
        if(cooperation.getStatus() == CoopStatus.APPROVED||cooperation.getStatus() == CoopStatus.STARTED)
            return true;
        else return false;

    }
}

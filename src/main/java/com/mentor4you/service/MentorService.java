package com.mentor4you.service;

import com.mentor4you.exception.MentorNotFoundException;
import com.mentor4you.model.*;
import com.mentor4you.model.DTO.MenteeResponseDTO;
import com.mentor4you.model.DTO.MenteeUpdateRequest;
import com.mentor4you.model.DTO.MentorGeneralResponseDTO;
import com.mentor4you.repository.AccountRepository;
import com.mentor4you.repository.MentorRepository;
import com.mentor4you.repository.UserRepository;
import com.mentor4you.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
@Service
public class MentorService {

    @Autowired
    AccountRepository accountRepository;
    MentorRepository mentorRepository;
    UserRepository userRepository;
    MenteeService menteeService;
    UserService userService;
    JwtProvider jwtProvider;


    public MentorService(AccountRepository accountRepository,
                         MentorRepository mentorRepository,
                         UserRepository userRepository,
                         MenteeService menteeService,
                         UserService userService,
                         JwtProvider jwtProvider) {
        this.accountRepository = accountRepository;
        this.mentorRepository = mentorRepository;
        this.userRepository = userRepository;
        this.menteeService = menteeService;
        this.userService = userService;
        this.jwtProvider = jwtProvider;
    }

    //select all mentor
    public List<Mentors> getFullInfoAllMentors(){
        int theMentors = accountRepository.findByRole(Role.MENTOR).size();
        if(theMentors!=0){
            return mentorRepository.findAll();
        }
        throw new MentorNotFoundException("Mentors not found");

    }
    //    select mentor by id
    public Optional<Mentors> getMentorById(int id){

        Optional<Mentors> theMentor = mentorRepository.findById(id).stream().filter(e->e.getId()==id).findFirst();
        if(theMentor.isPresent()) {
            return theMentor;
        }

        throw new MentorNotFoundException("Mentor with id = "+ id +" not found");

    }
    public ResponseEntity<MentorGeneralResponseDTO> getOneMentorByToken(HttpServletRequest request){
        String token = jwtProvider.getTokenFromRequest(request);

        String email = jwtProvider.getLoginFromToken(token);

        User user = userRepository.findUserByEmail(email);

        MenteeResponseDTO mDTO = userService.getOneMentee(user).getBody();

        try{

            Mentors m = mentorRepository.getById(user.getId());

            MentorGeneralResponseDTO dto =new MentorGeneralResponseDTO();
            dto.setAccountInfo(mDTO);
            dto.setCertificats(m.getCertificats());
            dto.setEducations(m.getEducations());
            dto.setDescription(m.getDescription());
            dto.setCategories(m.getMentors_to_categories());
            dto.setOnline(m.isOnline());
            dto.setOfflineIn(m.isOfflineIn());
            dto.setOfflineOut(m.isOfflineOut());
            dto.setShowable_status(m.isShowable_status());

            return new ResponseEntity<MentorGeneralResponseDTO>(dto, HttpStatus.OK);
        }
        catch (EntityNotFoundException e)
        {
            return new ResponseEntity<MentorGeneralResponseDTO>(HttpStatus.NOT_FOUND);
        }

    }
}

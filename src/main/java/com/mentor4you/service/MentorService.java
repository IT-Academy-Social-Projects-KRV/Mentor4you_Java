package com.mentor4you.service;

import com.mentor4you.exception.MentorNotFoundException;
import com.mentor4you.model.*;
import com.mentor4you.repository.AccountRepository;
import com.mentor4you.repository.MentorRepository;
import com.mentor4you.repository.MentorsToCategory;
import com.mentor4you.repository.UserRepository;
import com.mentor4you.service.requests.MentorGeneralDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class MentorService {

    @Autowired
    MentorsToCategory mentorsToCategory;
    AccountRepository accountRepository;
    MentorRepository mentorRepository;
    UserRepository userRepository;

    public MentorService(MentorsToCategory mentorsToCategory, AccountRepository accountRepository, MentorRepository mentorRepository, UserRepository userRepository) {
        this.mentorsToCategory = mentorsToCategory;
        this.accountRepository = accountRepository;
        this.mentorRepository = mentorRepository;
        this.userRepository = userRepository;
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
    // не фінальна версія
    public ResponseEntity<MentorGeneralDTO> getByEmail(String email){
        User user = userRepository.findUserByEmail(email);
            try
            {
                if(user ==null||user.getRole()!=Role.MENTOR)return new ResponseEntity<>(HttpStatus.NOT_FOUND) ;
                Mentors m = mentorRepository.getById(user.getId());
                MentorGeneralDTO dto =
                        new MentorGeneralDTO(m.getDescription(),
                                m.isShowable_status(),
                                m.isOnline(),
                                m.isOfflineIn(),
                                m.isOfflineOut(),
                                m.getEducations(),
                                m.getCertificats(),
                                m.getMentors_to_categories()
                        );
                return new ResponseEntity<MentorGeneralDTO>(dto, HttpStatus.OK);
            }
            catch (EntityNotFoundException e)
            {
                return new ResponseEntity<MentorGeneralDTO>(HttpStatus.NOT_FOUND);
            }

    }


    public ResponseEntity<String> updateGeneralDataMentors(String email , MentorGeneralDTO up){
        User user = userRepository.findUserByEmail(email);
        if(user ==null||user.getRole()!=Role.MENTOR)return new ResponseEntity<String>("update was Unsuccessful",HttpStatus.NOT_FOUND) ;
        try {
            Mentors mentor = mentorRepository.getById(user.getId());
            mentor.setCertificats(up.getCertificats());
            mentor.setEducations(up.getEducations());
            mentor.setDescription(up.getDescription());
            mentor.setOnline(up.isOnline());
            mentor.setOfflineIn(up.isOfflineIn());
            mentor.setOfflineOut(up.isOfflineOut());
            remove(mentor);
            for (Mentors_to_categories n : up.getCategories()) {
                n.setMentors(mentor);
            }
            mentor.setMentors_to_categories(up.getCategories());
            mentorRepository.save(mentor);
            return new ResponseEntity<String>("update was successful", HttpStatus.OK);
        }catch (EntityNotFoundException e){
          //  catch()

        }
       9return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }
    public void remove(Mentors m){
        if(m.getMentors_to_categories()!=null)
        mentorsToCategory.removeByMentors(m);
    }
}

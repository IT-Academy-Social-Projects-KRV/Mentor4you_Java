package com.mentor4you.service;

import com.mentor4you.exception.MentorNotFoundException;
import com.mentor4you.model.*;
import com.mentor4you.repository.AccountRepository;
import com.mentor4you.repository.MentorRepository;
import com.mentor4you.repository.MentorsToCategory;
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

    public MentorService(MentorsToCategory mentorsToCategory, AccountRepository accountRepository, MentorRepository mentorRepository) {
        this.mentorsToCategory = mentorsToCategory;
        this.accountRepository = accountRepository;
        this.mentorRepository = mentorRepository;
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
    public ResponseEntity<MentorGeneralDTO> getById(int id){
        try {
            Mentors m = mentorRepository.getById(id);


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
        }catch (EntityNotFoundException e){
            return new ResponseEntity<MentorGeneralDTO>(HttpStatus.NOT_FOUND);
        }



    }
    public String updateGeneralDataMentors(int id , MentorGeneralDTO up){
        if(mentorRepository.getById(id)!=null){
            Mentors mentor =mentorRepository.getById(id);
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
            return "update was successful";
        }
        throw new MentorNotFoundException("Mentor with id = "+ id +" not found");
    }
    public void remove(Mentors m){
        mentorsToCategory.removeByMentors(m);
    }
}

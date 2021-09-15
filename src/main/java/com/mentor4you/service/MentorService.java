package com.mentor4you.service;

import com.mentor4you.exception.MentorNotFoundException;
import com.mentor4you.model.*;
import com.mentor4you.repository.AccountRepository;
import com.mentor4you.repository.MentorRepository;
import com.mentor4you.service.requests.MentorGeneralDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class MentorService {

    @Autowired
    AccountRepository accountRepository;
    MentorRepository mentorRepository;


    public MentorService(AccountRepository accountRepository,MentorRepository mentorRepository) {
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
    public MentorGeneralDTO getById(int id){

        Mentors m = mentorRepository.getById(id);
        MentorGeneralDTO dto =
                new MentorGeneralDTO(m.getDescription(),
                        false,
                        false,
                        false,
                        false,
                        null,
                        m.getEducations(),
                        m.getCertificats());

            return  dto;


        //throw new MentorNotFoundException("Mentor with id = "+ id +" not found");

    }
    public String updateGeneralDataMentors(int id , MentorGeneralDTO up){
        if(mentorRepository.getById(id)!=null){
            Mentors mentor =mentorRepository.getById(id);
            mentor.setCertificats(up.getCertificats());
            mentor.setEducations(up.getEducations());
            mentor.setDescription(up.getDescription());
            mentor.setIs_offline_in(true);
            mentor.setIs_offline_out(true);
            mentor.setIs_online(true);




            mentorRepository.save(mentor);
            return "update was successful";
        }
        throw new MentorNotFoundException("Mentor with id = "+ id +" not found");
    }
}

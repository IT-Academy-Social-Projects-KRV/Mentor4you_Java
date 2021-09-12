package com.mentor4you.service;

import com.mentor4you.exception.MentorNotFoundException;
import com.mentor4you.model.*;
import com.mentor4you.repository.AccountRepository;
import com.mentor4you.repository.MentorRepository;
import com.mentor4you.service.requests.UpdateAccountRequest;
import com.mentor4you.service.requests.UpdateMentorRequest;
import com.mentor4you.service.requests.UpdateUserRequest;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
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
    public UpdateMentorRequest getById(int id){


        Mentors theMentor = mentorRepository.getById(id);
        Accounts accounts =theMentor.getAccounts();
        User user =accounts.getUser();
        UpdateMentorRequest m =
                new UpdateMentorRequest(
                        theMentor.getDescription()
                        , theMentor.isShowable_status(),
                        false,
                        false,
                        false,
                        theMentor.getGroup_services(),
                        theMentor.getEducations(),
                        theMentor.getCertificats(),
                        new UpdateAccountRequest(
                                accounts.getPhoneNumber(),
                                accounts.getLanguagesList(),
                                new HashSet<Links_to_accounts>(),
                                new UpdateUserRequest(user.getFirst_name(),
                                        user.getLast_name(),
                                        user.getAvatar())
                        ));

            return m;


        //throw new MentorNotFoundException("Mentor with id = "+ id +" not found");

    }
    public String updateGeneralDataMentors(int id ,UpdateMentorRequest up){
        if(mentorRepository.getById(id)!=null){
            Mentors mentor =mentorRepository.getById(id);
            mentor.setCertificats(up.getCertificats());
            mentor.setEducations(up.getEducations());
            mentor.setDescription(up.getDescription());
            mentor.setIs_offline_in(true);
            mentor.setIs_offline_out(true);
            mentor.setIs_online(true);
            mentor.getAccounts().setLanguagesList(up.getAccountRequest().getLanguagesList());



            mentorRepository.save(mentor);
            return "update was successful";
        }
        throw new MentorNotFoundException("Mentor with id = "+ id +" not found");
    }
}

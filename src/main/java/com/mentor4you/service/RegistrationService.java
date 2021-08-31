package com.mentor4you.service;

import com.mentor4you.exception.UserAlreadyExistException;
import com.mentor4you.model.Accounts;
import com.mentor4you.model.DTO.UserDTO;
import com.mentor4you.model.Mentors;
import com.mentor4you.model.Role;
import com.mentor4you.model.User;
import com.mentor4you.repository.MentorRepository;
import com.mentor4you.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Locale;

@Service
public class RegistrationService{

    @Autowired
    MentorRepository mentorRepository;
    UserRepository userRepository;

    public RegistrationService(MentorRepository mentorRepository, UserRepository userRepository) {
        this.mentorRepository = mentorRepository;
        this.userRepository = userRepository;
    }

    public String registration(UserDTO userDTO) throws UserAlreadyExistException{

        String email = userDTO.getEmail();
        if(emailExist(email)){
            throw new UserAlreadyExistException("User with email = "+ email + " already exist");
        }
        //TODO: add email validator service

        User user = new User();
        Accounts accounts = new Accounts();

        user.setEmail(email);

        String encodePass = new BCryptPasswordEncoder().encode(userDTO.getPassword());

        user.setPassword(encodePass);

        user.setRegistration_date(LocalDateTime.now());

        user.setStatus(true);

        accounts.setUser(user);

        if(userDTO.getRole().equals("mentor")){ //true
            //role Mentor
            Mentors mentor = new Mentors();
            user.setRole(Role.MENTOR);
            mentor.setAccounts(accounts);
            mentorRepository.save(mentor);
        }else{
            //role mentee
            //TODO: add table Mentees and menteeRepository
//            Mentees mentee = new Mentees();
//            user.setRole(Role.MENTEE);
//            mentee.setAccounts(accounts);
//            menteeRepository.save(mentee);
        }
        return "User created";
    }

    private boolean emailExist(String email){
        return userRepository.findByEmail(email).isPresent();
    }

}

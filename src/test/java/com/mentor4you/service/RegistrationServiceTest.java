package com.mentor4you.service;

import com.mentor4you.model.*;
import com.mentor4you.model.DTO.UserDTO;
import com.mentor4you.repository.MenteeRepository;
import com.mentor4you.repository.MentorRepository;
import com.mentor4you.repository.UserRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
class RegistrationServiceTest {

    @Autowired
    private RegistrationService registrationService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private PasswordService passwordService;


    @MockBean
    private MentorRepository mentorRepository;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private MenteeRepository menteeRepository;


    @Test
    void registration() {

        UserDTO userDTO = new UserDTO("123@gmail.com","Password1","mentor");

        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setAvatar("https://awss3mentor4you.s3.eu-west-3.amazonaws.com/avatars/standartUserAvatar.png");
        user.setRegistration_date(LocalDateTime.now());
        user.setStatus(true);
        user.setBan(false);
        user.setPassword(passwordService.encodePassword(userDTO.getPassword()));
        Accounts accounts = new Accounts();
        accounts.setUser(user);


        boolean isUserCreated = registrationService.registration(userDTO).equals("User created");

        Assert.assertTrue(isUserCreated);
        Assert.assertFalse(emailService.emailExist(user.getEmail()));
        Assert.assertTrue(passwordService.equalsPassword(userDTO.getPassword(),user.getPassword()));
        Assert.assertTrue(user.getStatus());
        Assert.assertFalse(user.getBan());
        Assert.assertNotNull(user.getEmail());
        Assert.assertNotNull(user.getAvatar());
        Assert.assertNotNull(user.getRegistration_date());

        Mentors mentor = new Mentors();
        Mockito.verify(mentorRepository, Mockito.times(1)).save(mentor);
        Mentees mentee = new Mentees();
        Mockito.verify(menteeRepository, Mockito.times(0)).save(mentee);
    }

    @Test
    void registrationEmailFailTest(){
        UserDTO userDTO = new UserDTO("123@gmail.com","Password1","mentor");

        Assert.assertTrue(!emailService.emailExist(userDTO.getEmail()));
    }

    @Test
    void registrationPasswordFailTest(){
        UserDTO userDTO = new UserDTO("123@gmail.com","Password1","mentor");

        User user = new User();
        user.setPassword(passwordService.encodePassword(userDTO.getPassword()));

        Assert.assertFalse(!passwordService.equalsPassword(userDTO.getPassword(),user.getPassword()));
    }
}
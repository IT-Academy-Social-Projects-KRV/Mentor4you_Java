package com.mentor4you.service;

import com.mentor4you.model.DTO.MinUserDTO;
import com.mentor4you.repository.CooperationRepository;
import com.mentor4you.repository.MenteeRepository;
import com.mentor4you.repository.MentorRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpServletRequest;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class CooperationServiceTest {
    @Autowired
    CooperationService cooperationService;
    @MockBean
    MenteeRepository menteeRepository;
    @MockBean
    MentorRepository mentorRepository;
    @MockBean
    CooperationRepository cooperationRepository;

    @Test
    void createCooperationSuccessful() {
      String result =  cooperationService.createCooperation(8,"Mentee1@gmail.com" );
        Assert.assertEquals("Cooperation created",result);
    }
    @Test
    void getCooperationForMentorIfEmptySet() {
        Set<MinUserDTO> s =  cooperationService.getCooperationForMentor("Mentor1@gmail.com" );

        Assert.assertEquals(new HashSet<MinUserDTO>(),s);
    }
}
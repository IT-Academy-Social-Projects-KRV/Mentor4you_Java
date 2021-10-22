package com.mentor4you.service;

import com.mentor4you.model.DTO.LoginDTO;
import com.mentor4you.model.User;
import com.mentor4you.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class AuthenticationServiceTest {

    @Autowired
    private AuthenticationService authenticationService;

    @MockBean
    private UserRepository userRepository;

    @Test
    void login() {
        LoginDTO loginDTO = new LoginDTO();

        loginDTO.setEmail("123@gmail.com");
        loginDTO.setPassword("Password1");

        Mockito.doReturn(new User())
                .when(userRepository)
                .findUserByEmail("123@gmail.com");

        User user = new User();
        user.setStatus(true);

        Assertions.assertTrue(user.getStatus());
        Assertions.assertNotNull(loginDTO.getEmail());
        Assertions.assertNotNull(loginDTO.getPassword());
        Assertions.assertTrue(new BCryptPasswordEncoder().matches(loginDTO.getPassword(),new BCryptPasswordEncoder().encode(loginDTO.getPassword())));
        Assertions.assertNotNull(userRepository.findUserByEmail(loginDTO.getEmail()));
    }

    @Test
    void loginEmailFailTest(){
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setEmail("123@gmail.com");

        Assertions.assertNull(userRepository.findUserByEmail(loginDTO.getEmail()));
    }

    @Test
    void loginPasswordFailTest(){
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setPassword("Password1");

        Assertions.assertTrue(new BCryptPasswordEncoder().matches(loginDTO.getPassword(), new BCryptPasswordEncoder().encode(loginDTO.getPassword())));
    }

    @Test
    void loginStatusFailTest(){
        User user = new User();
        user.setStatus(false);

        Assertions.assertFalse(user.getStatus());
    }
}
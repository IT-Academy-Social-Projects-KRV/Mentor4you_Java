package com.mentor4you.service;

import com.mentor4you.model.DTO.LoginDTO;
import com.mentor4you.model.User;
import com.mentor4you.repository.UserRepository;
import com.mentor4you.security.jwt.JwtProvider;
import com.mentor4you.security.jwt.OnUserLogoutSuccessEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class AuthenticationService {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;
    JwtProvider jwtProvider;

    public AuthenticationService(UserRepository userRepository, JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
    }


    public String login(LoginDTO request){
        User user = userRepository.findUserByEmail(request.getEmail());

        if(user.getStatus() && new BCryptPasswordEncoder().matches(request.getPassword(),user.getPassword())){

            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    user.getEmail(),
                    user.getPassword()
            ));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String authToken = jwtProvider.generateAuthToken(authentication);
            return authToken;
        }

        return "";
    }

    public String logout(HttpServletRequest request) {
        String token = jwtProvider.getTokenFromRequest(request);
        String email = jwtProvider.getLoginFromToken(token);

        OnUserLogoutSuccessEvent logoutEventPublisher = new OnUserLogoutSuccessEvent(email,token);
        applicationEventPublisher.publishEvent(logoutEventPublisher);

        return email + " "+ token;
    }
}

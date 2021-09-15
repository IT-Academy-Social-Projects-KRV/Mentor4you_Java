package com.mentor4you.service;

import com.mentor4you.model.DTO.LoginDTO;
import com.mentor4you.model.User;
import com.mentor4you.repository.UserRepository;
import com.mentor4you.security.jwt.CustomUserDetails;
import com.mentor4you.security.jwt.JwtProvider;
import com.mentor4you.security.jwt.cache.event.OnUserLogoutSuccessEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
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


    public String login(LoginDTO request) throws AuthenticationException {
        User user = userRepository.findUserByEmail(request.getEmail());

        if(user==null){
            throw new AuthenticationException("Email is incorrect");
        }

        if(user.getStatus() && new BCryptPasswordEncoder().matches(request.getPassword(),user.getPassword())){
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.getEmail(),
                    request.getPassword()
            ));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return jwtProvider.generateAuthToken(authentication);
        }

        throw new AuthenticationException("Password is incorrect");
    }

    public String logout(HttpServletRequest request, CustomUserDetails user) {
        String token = jwtProvider.getTokenFromRequest(request);
        OnUserLogoutSuccessEvent logoutEventPublisher = new OnUserLogoutSuccessEvent(user.getUsername(),token);
        applicationEventPublisher.publishEvent(logoutEventPublisher);

        return "You have successfully logout";
    }
}

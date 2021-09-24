package com.mentor4you.service;

import com.mentor4you.exception.AdminDeleteException;
import com.mentor4you.exception.RegistrationException;
import com.mentor4you.model.DTO.UserBanDTO;
import com.mentor4you.model.User;
import com.mentor4you.repository.UserRepository;
import com.mentor4you.security.jwt.JwtProvider;
import com.mentor4you.security.jwt.cache.event.OnUserLogoutSuccessEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    ApplicationEventPublisher applicationEventPublisher;
    PasswordService passwordService;
    UserRepository userRepository;
    JwtProvider jwtProvider;
    AuthenticationService authenticationService;

    public UserService(ApplicationEventPublisher applicationEventPublisher, UserRepository userRepository, JwtProvider jwtProvider, AuthenticationService authenticationService) {
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
        this.authenticationService = authenticationService;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    //select all users
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public String changePassword(HttpServletRequest request, String oldPassword, String newPassword) {
        String token = jwtProvider.getTokenFromRequest(request);
        String email =jwtProvider.getLoginFromToken(token);

        User user = userRepository.findUserByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found ");
        }
        if(passwordService.equalsPassword(oldPassword, user.getPassword())){
            if(!passwordService.isValidPassword(newPassword)){
                throw new RegistrationException("Password is not valid");
            }
            user.setPassword(passwordService.encodePassword(newPassword));
            userRepository.save(user);
            return "Password changed";
        }else{
            throw new RegistrationException("The old password is incorrect");
        }
    }


    public List<UserBanDTO> getAllBannedUsers(Boolean bool){

        List<User> userslist = userRepository.findByBan(bool);
        List<UserBanDTO> userBanList = new ArrayList<>();

            for (User user : userslist) {
                UserBanDTO userBan = new UserBanDTO();
                userBan.setId(user.getId());
                userBan.setRole(user.getRole());
                userBan.setEmail(user.getEmail());
                userBan.setFirst_name(user.getFirst_name());
                userBan.setLast_name(user.getLast_name());
                userBan.setAvatar(user.getAvatar());
                userBan.setBan(user.getBan());

                userBanList.add(userBan);
            }
            return userBanList;
    }



    public String changeBanToUser(Boolean bool, int id){
        User user = userRepository.findOneById(id);
        if (user == null) {
            throw new UsernameNotFoundException("User not found ");
        }
        else {
            if(user.getBan()!=bool){user.setBan(bool);
                userRepository.save(user);
                return "User's ban status changed";}
            else { return "User ban status has not been changed";}
        }
    }

    public String deleteUser(HttpServletRequest request) {
        String token = jwtProvider.getTokenFromRequest(request);
        String email = jwtProvider.getLoginFromToken(token);
        User user = userRepository.findUserByEmail(email);

        if(user.getRole().name().equals("ADMIN")){
            throw new AdminDeleteException("You can not delete ADMIN account");
        }

        user.setStatus(false);
        userRepository.save(user);

        OnUserLogoutSuccessEvent logoutEventPublisher = new OnUserLogoutSuccessEvent(user.getEmail(),token);
        applicationEventPublisher.publishEvent(logoutEventPublisher);


        return "Account been deleted";
    }
}

package com.mentor4you.service;

import com.mentor4you.exception.MenteeNotFoundException;
import com.mentor4you.exception.RegistrationException;
import com.mentor4you.model.DTO.MenteeUpdateRequest;
import com.mentor4you.model.DTO.UserBanDTO;
import com.mentor4you.model.Role;
import com.mentor4you.model.User;
import com.mentor4you.repository.UserRepository;
import com.mentor4you.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    PasswordService passwordService;
    UserRepository userRepository;
    JwtProvider jwtProvider;
    EmailService emailService;
    ContactsToAccountsService contactsToAccountsService;

    public UserService(UserRepository userRepository,
                       JwtProvider jwtProvider,
                       EmailService emailService,
                       ContactsToAccountsService contactsToAccountsService) {
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
        this.emailService = emailService;
        this.contactsToAccountsService = contactsToAccountsService;
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

    public ResponseEntity<String> updateUserByToken(@RequestBody MenteeUpdateRequest request,
                                                      HttpServletRequest req4){
        String token = jwtProvider.getTokenFromRequest(req4);
        String emailFromToken = jwtProvider.getLoginFromToken(token);

        String emailNew = request.getEmail();
        User userToUpdate = userRepository.findUserByEmail(emailFromToken);
        int id = userToUpdate.getId();

        if (userToUpdate != null) {

                if(request.getFirstName().isEmpty()){userToUpdate.setFirst_name("");}
                else{userToUpdate.setFirst_name(request.getFirstName());}

                if(request.getLastName().isEmpty()){userToUpdate.setLast_name("");}
                else{ userToUpdate.setLast_name(request.getLastName());}

                //update email using method from emailService
                //if emails are equals do nothing
                if (!userToUpdate.getEmail().equals(emailNew)) {
                    //TODO create new token with new email
                    String reportUpdate = emailService.updateEmail(emailNew, id);
                }
                userRepository.save(userToUpdate);

                contactsToAccountsService.changeContactsDataUser(request, id);

        } else {
            throw new MenteeNotFoundException("Mentees with id = " + id + " not found");
        }
        return new ResponseEntity<String>("ok", HttpStatus.OK);
    }

}

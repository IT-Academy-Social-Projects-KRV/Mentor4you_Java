package com.mentor4you.service;

import com.mentor4you.exception.RegistrationException;
import com.mentor4you.model.DTO.UserBanUpdateRequest;
import com.mentor4you.model.User;
import com.mentor4you.repository.MenteeRepository;
import com.mentor4you.repository.MentorRepository;
import com.mentor4you.repository.UserRepository;
import com.mentor4you.service.EmailService;
import com.mentor4you.service.PasswordService;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.keygen.BytesKeyGenerator;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.mail.MessagingException;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Random;


@Service
public class ResetPasswordService {

    private static final BytesKeyGenerator DEFAULT_TOKEN_GENERATOR = KeyGenerators.secureRandom(15);
    private static final Charset US_ASCII = Charset.forName("US-ASCII");
    private static final Random RANDOM = new SecureRandom();
    @Autowired
    EmailService emailService;
    PasswordService passwordService;
    UserRepository userRepository;


    public ResetPasswordService(EmailService emailService, PasswordService passwordService, UserRepository userRepository) {
        this.emailService = emailService;
        this.passwordService = passwordService;
        this.userRepository = userRepository;

    }
    public void chekemail(String email) throws MessagingException {
        String password = "pAssword12345";

        User user = userRepository.findUserByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found ");
        }

        if (!passwordService.isValidPassword(password)) {
            throw new RegistrationException("Password is not valid");
        }
        user.setPassword(passwordService.encodePassword(password));
        userRepository.save(user);
        if (emailService.emailExist(email)) {
            emailService.resetPasswordmessage(email, "" , generateRandomPassword() );

        }

    }
    public static String generateRandomPassword() {
        String letters = "abcdefghjkmnpqrstuvwxyzABCDEFGH1234567891234567890JKMNPQRSTUVWXYZ";

        String pw = "";
        for (int i = 0; i < 10; i++) {
            int index = (int) (RANDOM.nextDouble() * letters.length());
            pw += letters.substring(index, index + 1);
        }
        return pw;
    }

}

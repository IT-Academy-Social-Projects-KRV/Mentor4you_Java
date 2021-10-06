package com.mentor4you.service;

import com.mentor4you.model.User;
import com.mentor4you.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class EmailService {

    @Autowired
    UserRepository userRepository;
    JavaMailSender emailSender;

    public EmailService(UserRepository userRepository, JavaMailSender emailSender) {
        this.userRepository = userRepository;
        this.emailSender = emailSender;
    }

    //check user email is existing in database
    public boolean emailExist(String email){
        return userRepository.findByEmail(email).isPresent();
    }

    public boolean isEmailValidRegEx(String email) {
        // get emails name length it must be not more 129
        int length = email.length();

        // simple pattern check @  .
        Pattern pattern = Pattern.compile(".+@.+\\..+");
        Matcher matcher = pattern.matcher(email);

        return (matcher.matches() && length<130);
    }

    public String updateEmail(String email, int id)
    {

        //TODO check email to valid with sending testEmail
        if (isEmailValidRegEx(email)){

            if (userRepository.findByEmail(email).isEmpty()) {

                User userToUpdate = userRepository.findOneById(id);
                userToUpdate.setEmail(email);
                userRepository.save(userToUpdate);
                return "Email updated to "+ userRepository.findOneById(id).getEmail();
            }
            else { return "email "+email+" is exist";}
        }
        else {return "Something wrong with thr email ->  "+email;}
    }


    public String sendEmailRandomCode(String sendTo, String messageText, String code) throws MessagingException {

        // Create a Simple MailMessage.
        //SimpleMailMessage message = new SimpleMailMessage();
        //message.setTo(sendTo);
        //message.setSubject("Test Simple Email");
        //message.setText("Hello, Im testing Simple Email");

        // Send Message!
        //this.emailSender.send(message)


        MimeMessage message = emailSender.createMimeMessage();
        boolean multipart = true;

        MimeMessageHelper helper = new MimeMessageHelper(message, multipart, "utf-8");

        String htmlMsg = "<h3>Im testing send a HTML email</h3>"
                +code;

        message.setContent(htmlMsg, "text/html");

        helper.setTo(sendTo);

        helper.setSubject("Test send HTML email");


        this.emailSender.send(message);

        return "Email Sent!";
    }

    public void sendNotificationToEmail(String to, String text) throws MessagingException{

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");

        String htmlMsg = "<h3>"+text+"</h3>";

        message.setContent(htmlMsg, "text/html");

        helper.setTo(to);

        helper.setSubject("Mentor4You team <3");

        this.emailSender.send(message);
    }

}

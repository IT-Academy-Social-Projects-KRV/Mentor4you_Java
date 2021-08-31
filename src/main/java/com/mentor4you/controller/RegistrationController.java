package com.mentor4you.controller;

import com.mentor4you.exception.ErrorObject;
import com.mentor4you.exception.MentorNotFoundException;
import com.mentor4you.exception.UserAlreadyExistException;
import com.mentor4you.model.DTO.UserDTO;
import com.mentor4you.service.RegistrationService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/registration")
public class RegistrationController {


    @Autowired
    RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    //method for registration
    @Operation(summary = "method for registration (role can be equals only 'mentor' or 'mentee')")
    @PostMapping
    String registration(@RequestBody UserDTO userDTO){
        return registrationService.registration(userDTO);
    }


    @ExceptionHandler
    public ResponseEntity<ErrorObject> handleException(UserAlreadyExistException ex) {
        ErrorObject eObject = new ErrorObject();
        eObject.setStatus(HttpStatus.BAD_REQUEST.value());
        eObject.setMessage(ex.getMessage());
        eObject.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(eObject, HttpStatus.BAD_REQUEST);
    }
}

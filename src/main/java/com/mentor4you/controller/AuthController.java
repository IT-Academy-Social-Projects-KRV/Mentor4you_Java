package com.mentor4you.controller;

import com.mentor4you.model.DTO.LoginDTO;
import com.mentor4you.security.jwt.cache.CurrentUser;
import com.mentor4you.security.jwt.CustomUserDetails;
import com.mentor4you.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO request){
        String token = authenticationService.login(request);
        return ResponseEntity.ok(token);
    }

    @PutMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, @CurrentUser CustomUserDetails user){
        return ResponseEntity.ok(authenticationService.logout(request, user));
    }
}

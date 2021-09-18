package com.mentor4you.controller;

import com.mentor4you.model.DTO.LoginDTO;
import com.mentor4you.security.jwt.cache.CurrentUser;
import com.mentor4you.security.jwt.CustomUserDetails;
import com.mentor4you.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

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
        Map<String, String> res = new HashMap<>();
        try {
            String token = authenticationService.login(request);
            res.put("message","You have successfully logged in");
            res.put("token", token);
            return ResponseEntity.ok(res);
        }catch (AuthenticationException ex) {
            res.put("message", ex.getMessage());
            return ResponseEntity.badRequest().body(res);
        }
    }

    @PutMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, @CurrentUser CustomUserDetails user){
        Map<String, String> res = new HashMap<>();
        String message = authenticationService.logout(request, user);
        res.put("message", message);
        return ResponseEntity.ok(res);
    }
}

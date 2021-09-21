package com.mentor4you.controller;


import com.mentor4you.exception.InvalidTokenRequestException;
import com.mentor4you.exception.JwtAuthenticationException;

import com.mentor4you.model.DTO.LoginDTO;
import com.mentor4you.model.DTO.SecureLoginDTO;
import com.mentor4you.security.jwt.cache.CurrentUser;
import com.mentor4you.security.jwt.CustomUserDetails;
import com.mentor4you.service.AuthenticationService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


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
    @PostMapping("/extralogin")
    public ResponseEntity<?> extralogin(@RequestBody SecureLoginDTO request){
        Map<String, String> res = new HashMap<>();
        try {
            String token = authenticationService.loginwithoutpassword(request);
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

    @PostMapping("/check")
    public ResponseEntity<?> checkTokenExpire(@RequestBody Map<String,String> request){
        Map<String, String> res = new HashMap<>();
        try{
            String message = authenticationService.checkExpiration(request.get("token"));
            res.put("message", message);
            return ResponseEntity.ok(res);
        }catch (JwtAuthenticationException | ExpiredJwtException e){
            res.put("message",e.getMessage());
            return ResponseEntity.status(401).body(res);
        }
    }

}

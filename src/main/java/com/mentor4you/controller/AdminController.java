package com.mentor4you.controller;

import com.mentor4you.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @Operation(summary = "Appoint a new moderator")
    @PutMapping("/appointModerator")
    ResponseEntity<?> changeAvatar(@RequestParam("userEmail") String email) {
        String result = adminService.appointModerator(email);
        return new ResponseEntity<String>(result, HttpStatus.OK);
    }
}

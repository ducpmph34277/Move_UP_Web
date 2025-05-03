package com.project.move_up_web.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
//@RequestMapping("/auth")
public class AuthController {
    @GetMapping("/welcome")
    public String welcome() {
        return "Hello";
    }

//    @GetMapping("/user/userProfile")
//    @PreAuthorize("hasRole('USER')")  // Use hasRole for role-based access control
//    public String userProfile() {
//        return "Welcome to User Profile";
//    }
//
//    @GetMapping("/admin/adminProfile")
//    @PreAuthorize("hasRole('ADMIN')")  // Use hasRole for role-based access control
//    public String adminProfile() {
//        return "Welcome to Admin Profile";
//    }
}

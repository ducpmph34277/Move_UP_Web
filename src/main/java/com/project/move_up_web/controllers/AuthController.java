package com.project.move_up_web.controllers;

import com.project.move_up_web.dtos.requests.UserRegisterRequest;
import com.project.move_up_web.services.AccountDetailsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AccountDetailsService accountDetailsService;

    @PostMapping("/register")
    public ResponseEntity<Object> register(@Valid @RequestBody UserRegisterRequest userRegisterRequest) {
        return ResponseEntity.ok(accountDetailsService.registerUser(userRegisterRequest));
    }
}

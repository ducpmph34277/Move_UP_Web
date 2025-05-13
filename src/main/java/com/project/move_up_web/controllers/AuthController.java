package com.project.move_up_web.controllers;

import com.project.move_up_web.dtos.requests.UserLoginRequest;
import com.project.move_up_web.dtos.requests.UserRegisterRequest;
import com.project.move_up_web.repositories.UserRepository;
import com.project.move_up_web.services.AccountDetailsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Autowired
    private AccountDetailsService accountDetailsService;

    @PostMapping("/register")
    public ResponseEntity<Object> register(@Valid @RequestBody UserRegisterRequest userRegisterRequest) {
        return ResponseEntity.ok(accountDetailsService.registerUser(userRegisterRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login (@Valid @RequestBody UserLoginRequest userLoginRequest) {
        return ResponseEntity.ok(accountDetailsService.login(userLoginRequest));
    }
}

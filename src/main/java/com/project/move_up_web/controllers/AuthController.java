package com.project.move_up_web.controllers;

import com.project.move_up_web.dtos.UserRegisterDto;
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
    public ResponseEntity<Object> register(@Valid @RequestBody UserRegisterDto userRegisterDto) {
        return ResponseEntity.ok(accountDetailsService.registerUser(userRegisterDto));
    }
}

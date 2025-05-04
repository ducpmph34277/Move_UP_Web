package com.project.move_up_web.controllers;

import com.project.move_up_web.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping("/welcome")
  public ResponseEntity<Object> welcome() {
    return ResponseEntity.ok(userService.findAll());
  }
}

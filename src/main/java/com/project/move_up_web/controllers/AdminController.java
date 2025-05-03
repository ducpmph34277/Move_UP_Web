package com.project.move_up_web.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/admin")
public class AdminController {
  @GetMapping("/welcome")
  public String welcome() {
    return "Welcome to the admin controller";
  }
}

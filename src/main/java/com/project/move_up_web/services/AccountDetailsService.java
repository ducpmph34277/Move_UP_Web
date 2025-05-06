package com.project.move_up_web.services;

import com.project.move_up_web.dtos.requests.UserRegisterRequest;
import com.project.move_up_web.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AccountDetailsService {
  UserDetailsService userDetailsService();

  User registerUser(UserRegisterRequest userRegisterRequest);
}

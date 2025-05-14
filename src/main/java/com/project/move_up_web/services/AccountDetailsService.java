package com.project.move_up_web.services;

import com.project.move_up_web.dtos.requests.UserLoginRequest;
import com.project.move_up_web.dtos.requests.UserRegisterRequest;
import com.project.move_up_web.entities.Users;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AccountDetailsService {
  UserDetailsService userDetailsService();

  Users registerUser(UserRegisterRequest userRegisterRequest);

  Object login(UserLoginRequest userLoginRequest);
}

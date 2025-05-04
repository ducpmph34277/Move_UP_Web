package com.project.move_up_web.services;

import com.project.move_up_web.dtos.UserRegisterDto;
import com.project.move_up_web.entities.Users;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AccountDetailsService {
  UserDetailsService userDetailsService();

  Users registerUser(UserRegisterDto userRegisterDto);
}

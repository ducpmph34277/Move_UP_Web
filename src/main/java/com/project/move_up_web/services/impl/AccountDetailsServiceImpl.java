package com.project.move_up_web.services.impl;

import com.project.move_up_web.entities.Users;
import com.project.move_up_web.repositories.UserRepository;
import com.project.move_up_web.services.AccountDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AccountDetailsServiceImpl implements AccountDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetailsService userDetailsService() {
    return username -> {
      Users user = userRepository.findByEmail(username);
      if (user == null) {
        System.out.println("User not found");
        throw new UsernameNotFoundException("User not found");
      }
      return user;
    };
  }
}

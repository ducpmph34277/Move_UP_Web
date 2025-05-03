package com.project.move_up_web.services;

import com.project.move_up_web.entities.Users;
import com.project.move_up_web.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public interface AccountDetailsService {
  UserDetailsService userDetailsService();
}

package com.project.move_up_web.services.impl;

import com.project.move_up_web.dtos.requests.UserRegisterRequest;
import com.project.move_up_web.dtos.mappers.UserMapper;
import com.project.move_up_web.entities.Role;
import com.project.move_up_web.entities.User;
import com.project.move_up_web.repositories.RoleRepository;
import com.project.move_up_web.repositories.UserRepository;
import com.project.move_up_web.services.AccountDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountDetailsServiceImpl implements AccountDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private RoleRepository roleRepository;

  @Autowired
  private UserMapper userMapper;

  private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

  @Override
  public UserDetailsService userDetailsService() {
    return username -> userRepository.findByEmail(username)
      .orElseThrow(() -> {
        System.out.println("User not found");
        return new UsernameNotFoundException("User not found");
      });
  }

  @Override
  public User registerUser(UserRegisterRequest userRegisterRequest) {
    userRegisterRequest.setPassword(encoder.encode(userRegisterRequest.getPassword()));
    Role role = roleRepository.findById(userRegisterRequest.getRoleId())
      .orElseThrow(() -> new IllegalArgumentException("Invalid role ID"));
    User user = userMapper.fromRegisterDto(userRegisterRequest);
    user.setRole(role);
    return userRepository.save(user);
  }
}

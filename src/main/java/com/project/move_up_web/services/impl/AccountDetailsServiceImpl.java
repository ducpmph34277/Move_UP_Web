package com.project.move_up_web.services.impl;

import com.project.move_up_web.dtos.UserRegisterDto;
import com.project.move_up_web.dtos.mappers.UserMapper;
import com.project.move_up_web.entities.Users;
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
  private UserMapper userMapper;

  private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

  @Override
  public UserDetailsService userDetailsService() {
    return new UserDetailsService() {
      @Override
      public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findByEmail(username);
        if (user == null) {
          System.out.println("User not found");
          throw new UsernameNotFoundException("User not found");
        }
        return user;
      }
    };
  }

  @Override
  public Users registerUser(UserRegisterDto userRegisterDto) {
    userRegisterDto.setPassword(encoder.encode(userRegisterDto.getPassword()));
    Users user = userMapper.fromRegisterDto(userRegisterDto);
    userRepository.save(user);
    return user;
  }
}

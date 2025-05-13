package com.project.move_up_web.services.impl;

import com.project.move_up_web.dtos.requests.UserLoginRequest;
import com.project.move_up_web.dtos.requests.UserRegisterRequest;
import com.project.move_up_web.dtos.mappers.UserMapper;
import com.project.move_up_web.entities.Users;
import com.project.move_up_web.repositories.UserRepository;
import com.project.move_up_web.securities.JwtService;
import com.project.move_up_web.services.AccountDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AccountDetailsServiceImpl implements AccountDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserMapper userMapper;

  @Autowired
  private JwtService jwtService;

  private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

  @Override
  public UserDetailsService userDetailsService() {
    return username -> userRepository.findByEmail(username)
      .orElseThrow(() -> {return new UsernameNotFoundException("User not found");
      });
  }

  @Override
  public Users registerUser(UserRegisterRequest userRegisterRequest) {
    userRegisterRequest.setPassword(encoder.encode(userRegisterRequest.getPassword()));
    Users users = userMapper.fromRegisterDto(userRegisterRequest);
    return userRepository.save(users);
  }

  public Object login(UserLoginRequest userLoginRequest) {
    System.out.println("Email: " + userLoginRequest.getEmail());
    System.out.println("Password: " + userLoginRequest.getPassword());
    Users users = userRepository.findByEmail(userLoginRequest.getEmail()).orElse(null);
    if(users == null) {
      throw new UsernameNotFoundException("Email không chính xác, vui lòng kiểm tra lại");
    }

    if(!encoder.matches(userLoginRequest.getPassword(), users.getPassword())) {
      throw new BadCredentialsException("Sai mật khẩu, vui lòng thử lại");
    }

    String token = jwtService.generateToken(users);

    Map<String, Object> response = new HashMap<>();
    response.put("token", token);
    response.put("user", userMapper.toUserInfoResponse(users));

    return response;
  }
}

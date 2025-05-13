package com.project.move_up_web.services.impl;

import com.project.move_up_web.dtos.mappers.UserMapper;
import com.project.move_up_web.dtos.responses.UserListResponse;
import com.project.move_up_web.entities.Users;
import com.project.move_up_web.repositories.UserRepository;
import com.project.move_up_web.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserMapper userMapper;

  @Autowired
  private UserRepository userRepository;

  @Override
  public List<UserListResponse> findAll() {
    List<Users> users = userRepository.findAll();
    return userMapper.toUserListResponse(users);
  }
}

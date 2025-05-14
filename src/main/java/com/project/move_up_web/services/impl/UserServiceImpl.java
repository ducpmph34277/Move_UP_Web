package com.project.move_up_web.services.impl;

import com.project.move_up_web.dtos.mappers.UserMappers;
import com.project.move_up_web.dtos.responses.UserListResponse;
import com.project.move_up_web.entities.Users;
import com.project.move_up_web.repositories.UserRepository;
import com.project.move_up_web.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

//  @Override
//  public List<UserListResponse> findAll() {
//    List<Users> users = userRepository.findAll();
//    return UserMappers.toUserListResponse(users);
//  }

  @Override
  public List<UserListResponse> findAll() {
    List<Users> users = userRepository.findAll();

    return users.stream()
            .map(UserMappers::toUserListResponse)
            .collect(Collectors.toList());
  }

}

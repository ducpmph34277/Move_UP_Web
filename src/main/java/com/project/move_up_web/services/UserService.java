package com.project.move_up_web.services;

import com.project.move_up_web.entities.Users;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
  List<Users> findAll();
}

package com.project.move_up_web.dtos.mappers;

import com.project.move_up_web.dtos.requests.UserRegisterRequest;
import com.project.move_up_web.dtos.responses.UserInfoResponse;
import com.project.move_up_web.dtos.responses.UserListResponse;
import com.project.move_up_web.entities.Users;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
  Users fromRegisterDto(UserRegisterRequest userRegisterRequest);

  UserListResponse toUserListResponse(Users users);

  List<UserListResponse> toUserListResponse(List<Users> users);

  UserInfoResponse toUserInfoResponse(Users users);
}

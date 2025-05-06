package com.project.move_up_web.dtos.mappers;

import com.project.move_up_web.dtos.requests.UserRegisterRequest;
import com.project.move_up_web.dtos.helpers.RolesMapperHelper;
import com.project.move_up_web.dtos.responses.UserListResponse;
import com.project.move_up_web.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = RolesMapperHelper.class)
public interface UserMapper {

  UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

  @Mapping(target = "role", source = "roleId", qualifiedByName = "mapRoleFromId")
  User fromRegisterDto(UserRegisterRequest userRegisterRequest);

  @Mapping(source = "role.roleName", target = "roleName")
  UserListResponse toUserListResponse(User user);

  List<UserListResponse> toUserListResponse(List<User> users);
}

package com.project.move_up_web.dtos.mappers;

import com.project.move_up_web.dtos.UserRegisterDto;
import com.project.move_up_web.entities.Users;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
  Users fromRegisterDto(UserRegisterDto userRegisterDto);
}

package com.project.move_up_web.dtos.mappers;

import com.project.move_up_web.configs.RolesEnum;
import com.project.move_up_web.dtos.requests.UserRegisterRequest;
import com.project.move_up_web.dtos.responses.UserListResponse;
import com.project.move_up_web.entities.Users;

public class UserMappers {

    public static Users toUser(UserRegisterRequest request) {
        if (request == null) {
            return null;
        }
        Users user = new Users();
        user.setEmail(request.getEmail());
        user.setFullName(request.getFullName());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setPassword(request.getPassword());
        user.setStatus(request.getStatus());

        if (request.getRoleName() != null) {
            try {
                user.setRoleName(RolesEnum.valueOf(request.getRoleName()));
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid role name: " + request.getRoleName());
            }
        }
        return user;
    }

    public static UserListResponse toUserListResponse(Users user) {
        if (user == null) {
            return null;
        }

        UserListResponse response = new UserListResponse();
        response.setEmail(user.getEmail());
        response.setAvatar(user.getAvatar());
        response.setFullName(user.getFullName());
        response.setPhoneNumber(user.getPhoneNumber());
        response.setGender(user.getGender());
        response.setDateOfBirth(user.getDateOfBirth());
        response.setRoleName(user.getRoleName() != null ? user.getRoleName().name() : null);
        response.setStatus(user.getStatus());
        response.setCreatedAt(user.getCreatedAt());
        response.setUpdatedAt(user.getUpdatedAt());
        response.setCreatedBy(user.getCreatedBy());
        response.setUpdatedBy(user.getUpdatedBy());

        return response;
    }
}

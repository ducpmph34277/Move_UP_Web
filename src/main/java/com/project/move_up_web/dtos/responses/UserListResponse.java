package com.project.move_up_web.dtos.responses;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserListResponse {
  private String email;
  private String avatar;
  private String fullName;
  private String phoneNumber;
  private Boolean gender;
  private LocalDateTime dateOfBirth;
  private String roleName;
  private String status;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private String createdBy;
  private String updatedBy;
}

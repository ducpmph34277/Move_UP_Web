package com.project.move_up_web.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterDto {
  @Email(message = "Invalid email")
  @NotBlank(message = "Email can't be blanked")
  private String email;
  @NotBlank(message = "Full name can't be blanked")
  private String fullName;
  @NotBlank(message = "Phone can't be blanked0")
  private String phoneNumber;
  @NotBlank(message = "Password can't be blanked")
  private String password;
  @NotBlank(message = "Status can't be blanked")
  private String status;
  @NotBlank(message = "Role can't be blanked")
  private Long roleId;
}

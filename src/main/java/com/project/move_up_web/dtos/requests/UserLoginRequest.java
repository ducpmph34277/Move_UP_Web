package com.project.move_up_web.dtos.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginRequest {
  @Email(message = "Invalid email")
  @NotBlank(message = "Email can't be blanked")
  private String email;
  @NotBlank(message = "Password can't be blanked")
  private String password;
}

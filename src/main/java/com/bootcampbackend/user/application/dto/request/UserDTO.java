package com.bootcampbackend.user.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
  @NotEmpty(message = "회사명은 비어있을 수 없습니다.")
  private String companyName;

  @NotEmpty(message = "상업자번호는 비어있을 수 없습니다.")
  private String businessNum;

  @NotEmpty(message = "이메일은 비어있을 수 없습니다.")
  private String email;

  @NotEmpty
  @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=])(?=\\S+$).{8,20}$")
  private String password;

  @NotEmpty
  @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=])(?=\\S+$).{8,20}$")
  private String passwordValid;
}

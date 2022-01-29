package com.bootcampbackend.user.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetUserDTO {
  @NotEmpty(message = "회사명은 비어있을 수 없습니다.")
  private String companyName;

  @NotEmpty(message = "상업자번호는 비어있을 수 없습니다.")
  private String businessNum;

  @NotEmpty(message = "이메일은 비어있을 수 없습니다.")
  private String email;
}

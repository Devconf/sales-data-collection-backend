package com.bootcampbackend.user.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetAuthorizedUserDTO {

  private String companyName;

  private String businessNum;

  private String email;

  private String token;
}

package com.bootcampbackend.user.application.common;

import com.bootcampbackend.user.application.dto.response.GetAuthorizedUserDTO;
import com.bootcampbackend.user.application.dto.response.GetUserDTO;
import com.bootcampbackend.user.domain.RoleType;
import com.bootcampbackend.user.domain.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

  User toUser(
      String companyName,
      String businessNum,
      String email,
      String password,
      RoleType role,
      String salt);

  GetUserDTO toUserDto(User user);

  GetAuthorizedUserDTO toAuthUserDto(
      String companyName, String businessNum, String email, RoleType role, String token);
}

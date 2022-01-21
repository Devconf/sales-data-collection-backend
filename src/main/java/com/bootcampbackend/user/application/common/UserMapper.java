package com.bootcampbackend.user.application.common;

import com.bootcampbackend.user.application.dto.request.UserDTO;
import com.bootcampbackend.user.application.dto.response.GetUserDTO;
import com.bootcampbackend.user.domain.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

  User toUser(UserDTO dto);

  GetUserDTO toUserDto(User user);
}

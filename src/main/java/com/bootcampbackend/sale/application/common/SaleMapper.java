package com.bootcampbackend.sale.application.common;

import com.bootcampbackend.sale.application.dto.response.GetAllUserDTO;
import com.bootcampbackend.user.application.dto.response.GetUserDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SaleMapper {

  default GetAllUserDTO toUserDtoList(List<GetUserDTO> dtoList) {
    GetAllUserDTO userDTO = new GetAllUserDTO(dtoList);
    return userDTO;
  }
}

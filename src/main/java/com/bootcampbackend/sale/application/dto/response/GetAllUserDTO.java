package com.bootcampbackend.sale.application.dto.response;

import com.bootcampbackend.user.application.dto.response.GetUserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GetAllUserDTO {
  private List<GetUserDTO> userList;
}

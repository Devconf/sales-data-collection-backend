package com.bootcampbackend.saleManage.application.common;

import com.bootcampbackend.saleManage.application.dto.response.GetAllUserDTO;
import com.bootcampbackend.saleManage.application.dto.response.GetSaleDTO;
import com.bootcampbackend.saleManage.domain.Sale;
import com.bootcampbackend.user.application.dto.response.GetUserDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SaleMangeMapper {

  default GetAllUserDTO toUserDtoList(List<GetUserDTO> dtoList) {
    GetAllUserDTO userDTO = new GetAllUserDTO(dtoList);
    return userDTO;
  }

  Sale toSale(
      String companyName,
      String businessNum,
      String email,
      Integer totalSales,
      Integer operatingProfit,
      Integer netIncome,
      String date);

  GetSaleDTO toGetSaleDto(Sale sale);
}

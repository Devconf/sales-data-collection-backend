package com.bootcampbackend.saleManage.application.common;

import com.bootcampbackend.saleManage.application.dto.response.GetAllUserDTO;
import com.bootcampbackend.saleManage.application.dto.response.GetSaleDTO;
import com.bootcampbackend.saleManage.domain.Sale;
import com.bootcampbackend.user.application.dto.response.GetUserDTO;
import org.mapstruct.Mapper;

import java.time.LocalDate;
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
      LocalDate date);

  default GetSaleDTO toGetSaleDto(Sale sale) {
    if (sale == null) {
      return null;
    }

    GetSaleDTO getSaleDTO = new GetSaleDTO();

    getSaleDTO.setUserId(sale.getSaleManage().getUser().getId());
    getSaleDTO.setSaleManageId(sale.getSaleManage().getId());
    getSaleDTO.setCompanyName(sale.getCompanyName());
    getSaleDTO.setBusinessNum(sale.getBusinessNum());
    getSaleDTO.setEmail(sale.getEmail());
    getSaleDTO.setTotalSales(sale.getTotalSales());
    getSaleDTO.setOperatingProfit(sale.getOperatingProfit());
    getSaleDTO.setNetIncome(sale.getNetIncome());
    getSaleDTO.setDate(sale.getDate());
    getSaleDTO.setAccessToken(sale.getAccessToken());

    return getSaleDTO;
  }
}

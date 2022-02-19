package com.bootcampbackend.saleManage.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateSaleDTO {
  private String companyName;

  private String businessNum;

  private String email;

  private Integer totalSales;

  private Integer operatingProfit;

  private Integer netIncome;

  private String date;
}

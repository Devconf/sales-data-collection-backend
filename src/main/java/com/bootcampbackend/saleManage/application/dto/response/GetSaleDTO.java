package com.bootcampbackend.saleManage.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GetSaleDTO {
  private Long id;

  private String companyName;

  private String businessNum;

  private String email;

  private Integer totalSales;

  private Integer operatingProfit;

  private Integer netIncome;

  private String date;

  private String accessToken;
}

package com.bootcampbackend.saleManage.application.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GetSaleDTO {
  private Long userId;

  private Long saleManageId;

  private String companyName;

  private String businessNum;

  private String email;

  private Integer totalSales;

  private Integer operatingProfit;

  private Integer netIncome;

  @JsonFormat(pattern = "yyyy/MM")
  private LocalDate date;

  private String accessToken;
}

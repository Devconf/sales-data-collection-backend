package com.bootcampbackend.saleManage.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Entity
public class Sale {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String companyName;

  private String businessNum;

  private Integer totalSales;

  private Integer operatingProfit;

  private Integer netIncome;

  private String date;

  private String accessToken;

  @ManyToOne
  @JoinColumn(name = "saleManageId")
  private SaleManage saleManage;

  public Sale(
      String companyName,
      String businessNum,
      Integer totalSales,
      Integer operatingProfit,
      Integer netIncome,
      String date) {
    this.companyName = companyName;
    this.businessNum = businessNum;
    this.totalSales = totalSales;
    this.netIncome = netIncome;
    this.operatingProfit = operatingProfit;
    this.date = date;
    this.accessToken = UUID.randomUUID().toString();
  }

  public void setSaleManage(SaleManage saleManage) {
    this.saleManage = saleManage;
  }
}

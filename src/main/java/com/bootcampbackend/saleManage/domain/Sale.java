package com.bootcampbackend.saleManage.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Entity
public class Sale {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String companyName;

  private String businessNum;

  private String email;

  private Integer totalSales;

  private Integer operatingProfit;

  private Integer netIncome;

  private LocalDate date;

  private String accessToken;

  @ManyToOne
  @JoinColumn(name = "saleManageId")
  private SaleManage saleManage;

  protected Sale() {}

  public Sale(
      String companyName,
      String businessNum,
      String email,
      Integer totalSales,
      Integer operatingProfit,
      Integer netIncome,
      LocalDate date) {
    this.companyName = companyName;
    this.businessNum = businessNum;
    this.email = email;
    this.totalSales = totalSales;
    this.netIncome = netIncome;
    this.operatingProfit = operatingProfit;
    this.date = date;
    this.accessToken = UUID.randomUUID().toString();
  }

  public void setSaleManage(SaleManage saleManage) {
    this.saleManage = saleManage;
  }

  public void updateSaleInfo(
      String companyName,
      String businessNum,
      String email,
      Integer totalSales,
      Integer operatingProfit,
      Integer netIncome,
      LocalDate date) {
    this.companyName = companyName;
    this.businessNum = businessNum;
    this.email = email;
    this.totalSales = totalSales;
    this.operatingProfit = operatingProfit;
    this.netIncome = netIncome;
    this.date = date;
    this.accessToken = UUID.randomUUID().toString();
  }
}

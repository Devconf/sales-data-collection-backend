package com.bootcampbackend.saleManage.domain;

import com.bootcampbackend.user.domain.User;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class SaleManage {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private LocalDate createdAt;

  private LocalDate updatedAt;

  @OneToMany(mappedBy = "saleManage", cascade = CascadeType.ALL)
  private List<Sale> sales = new ArrayList<>();

  @ManyToOne
  @JoinColumn(name = "userId")
  private User user;

  protected SaleManage() {}

  public SaleManage(User user) {
    this.user = user;
    this.createdAt = LocalDate.now();
    this.updatedAt = null;
  }

  public void addSale(Sale payload) {
    payload.setSaleManage(this);
    this.sales.add(payload);
  }

  public void updateSale(Sale updatePayload) {
    //
  }
}

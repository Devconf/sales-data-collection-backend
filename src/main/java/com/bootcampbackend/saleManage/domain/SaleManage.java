package com.bootcampbackend.saleManage.domain;

import com.bootcampbackend.user.domain.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class SaleManage {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;

  @OneToMany(mappedBy = "saleManage", cascade = CascadeType.ALL)
  private List<Sale> sales = new ArrayList<>();

  @ManyToOne
  @JoinColumn(name = "userId")
  private User user;

  protected SaleManage() {}

  public SaleManage(User user) {
    this.user = user;
  }

  public void addSale(Sale payload) {
    payload.setSaleManage(this);
    this.sales.add(payload);
  }

  public void updateSale(Sale updatePayload) {
    //
  }
}

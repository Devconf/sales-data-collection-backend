package com.bootcampbackend.saleManage.domain;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository {
  Sale save(Sale toSave);

  List<Sale> findSaleBetweenDate(LocalDate startAt, LocalDate endAt);

  List<Sale> findSaleByUserIdAndBetweenDate(Long userId, LocalDate startAt, LocalDate endAt);
}

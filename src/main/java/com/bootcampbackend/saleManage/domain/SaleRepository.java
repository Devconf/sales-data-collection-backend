package com.bootcampbackend.saleManage.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface SaleRepository {
  Sale save(Sale toSave);

  Optional<Sale> findSaleByAccessToken(String accessToken);

  List<Sale> findSaleBetweenDate(LocalDate startAt, LocalDate endAt);

  List<Sale> findSaleByUserIdAndBetweenDate(Long userId, LocalDate startAt, LocalDate endAt);
}

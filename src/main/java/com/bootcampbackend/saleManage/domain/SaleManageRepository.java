package com.bootcampbackend.saleManage.domain;

import java.util.Optional;

public interface SaleManageRepository {
  SaleManage save(SaleManage toSave);

  Optional<SaleManage> findSaleManageById(Long id);
}

package com.bootcampbackend.saleManage.infra;

import com.bootcampbackend.saleManage.domain.SaleManage;
import com.bootcampbackend.saleManage.domain.SaleManageRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaSaleManageRepository
    extends JpaRepository<SaleManage, Long>, SaleManageRepository {}

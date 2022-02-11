package com.bootcampbackend.saleManage.infra;

import com.bootcampbackend.saleManage.domain.Sale;
import com.bootcampbackend.saleManage.domain.SaleRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaSaleRepository extends JpaRepository<Sale, Long>, SaleRepository {}

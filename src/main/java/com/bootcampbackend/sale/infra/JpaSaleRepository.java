package com.bootcampbackend.sale.infra;

import com.bootcampbackend.sale.domain.Sale;
import com.bootcampbackend.sale.domain.SaleRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaSaleRepository extends JpaRepository<Sale, Long>, SaleRepository {}

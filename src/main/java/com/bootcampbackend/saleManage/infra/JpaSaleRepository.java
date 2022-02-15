package com.bootcampbackend.saleManage.infra;

import com.bootcampbackend.saleManage.domain.Sale;
import com.bootcampbackend.saleManage.domain.SaleRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface JpaSaleRepository extends JpaRepository<Sale, Long>, SaleRepository {

  @Query(
      "select s from Sale s where s.saleManage.createdAt >=:startAt and s.saleManage.createdAt <=:endAt")
  List<Sale> findSaleBetweenDate(
      @Param("startAt") LocalDate startAt, @Param("endAt") LocalDate endAt);

  @Query(
      "select s from Sale s where s.saleManage.user.id =:userId and s.saleManage.createdAt >=:startAt and s.saleManage.createdAt <=:endAt")
  List<Sale> findSaleByUserIdAndBetweenDate(
      @Param("userId") Long userId,
      @Param("startAt") LocalDate startAt,
      @Param("endAt") LocalDate endAt);
}

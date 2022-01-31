package com.bootcampbackend.sale.controller;

import com.bootcampbackend.sale.application.SaleQueryProcessor;
import com.bootcampbackend.sale.application.dto.request.PageRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sales")
@RequiredArgsConstructor
public class SaleController {

  private final SaleQueryProcessor saleQueryProcessor;

  @GetMapping(value = "/list")
  public ResponseEntity getUserList(PageRequestDTO pageRequestDTO) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(saleQueryProcessor.getUserList(pageRequestDTO));
  }
}

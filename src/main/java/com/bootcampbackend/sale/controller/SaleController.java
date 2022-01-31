package com.bootcampbackend.sale.controller;

import com.bootcampbackend.sale.application.SaleCommandExecutor;
import com.bootcampbackend.sale.application.SaleQueryProcessor;
import com.bootcampbackend.sale.application.dto.request.PageRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/sales")
@RequiredArgsConstructor
public class SaleController {

  private final SaleQueryProcessor saleQueryProcessor;
  private final SaleCommandExecutor saleCommandExecutor;

  @GetMapping(value = "/list")
  public ResponseEntity getUserList(PageRequestDTO pageRequestDTO) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(saleQueryProcessor.getUserList(pageRequestDTO));
  }

  @GetMapping(value = "/sendEmail")
  public ResponseEntity sendMail(@RequestParam("id") long id) {
    saleCommandExecutor.sendMail(id);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @GetMapping(value = "sendEmails")
  public ResponseEntity sendMailAll() {
    saleCommandExecutor.sendMailAll();
    return ResponseEntity.status(HttpStatus.OK).build();
  }
}

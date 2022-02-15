package com.bootcampbackend.saleManage.controller;

import com.bootcampbackend.saleManage.application.SaleCommandExecutor;
import com.bootcampbackend.saleManage.application.SaleQueryProcessor;
import com.bootcampbackend.saleManage.application.dto.request.DownloadRequestDTO;
import com.bootcampbackend.saleManage.application.dto.request.PageRequestDTO;
import com.bootcampbackend.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@Log4j2
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

  @GetMapping(value = "/sendEmails")
  public ResponseEntity sendMailAll() {
    saleCommandExecutor.sendMailAll();
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @PostMapping(value = "/upload")
  public ResponseEntity uploadFile(MultipartFile[] files, @AuthenticationPrincipal User user) {
    saleCommandExecutor.fileUpload(files, user);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @PostMapping(value = "/download")
  public ResponseEntity getSaleListWithUser(
      @Valid @RequestBody DownloadRequestDTO dto, @AuthenticationPrincipal User user) {
    ;
    return ResponseEntity.status(HttpStatus.OK)
        .body(saleQueryProcessor.getSalesWithUserRole(dto, user));
  }

  @PostMapping(value = "/download/all")
  public ResponseEntity getAllSaleList(
      @Valid @RequestBody DownloadRequestDTO dto, @AuthenticationPrincipal User user) {
    return ResponseEntity.status(HttpStatus.OK).body(saleQueryProcessor.getAllSales(dto, user));
  }
}

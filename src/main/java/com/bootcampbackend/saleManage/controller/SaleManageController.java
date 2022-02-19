package com.bootcampbackend.saleManage.controller;

import com.bootcampbackend.saleManage.application.SaleManageCommandExecutor;
import com.bootcampbackend.saleManage.application.SaleManageQueryProcessor;
import com.bootcampbackend.saleManage.application.dto.request.DownloadRequestDTO;
import com.bootcampbackend.saleManage.application.dto.request.PageRequestDTO;
import com.bootcampbackend.saleManage.application.dto.request.UpdateSaleDTO;
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
@RequestMapping("/apis/saleManage")
@RequiredArgsConstructor
public class SaleManageController {

  private final SaleManageQueryProcessor saleManageQueryProcessor;
  private final SaleManageCommandExecutor saleManageCommandExecutor;

  @GetMapping(value = "/users")
  public ResponseEntity getUserList(PageRequestDTO pageRequestDTO) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(saleManageQueryProcessor.getUserList(pageRequestDTO));
  }

  @GetMapping(value = "/email")
  public ResponseEntity sendMail(@RequestParam("id") long id) {
    saleManageCommandExecutor.sendMail(id);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @GetMapping(value = "/email/all")
  public ResponseEntity sendMailAll() {
    saleManageCommandExecutor.sendMailAll();
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @GetMapping(value = "/email/invite/{accessToken}")
  public ResponseEntity sendMailWithAccessToken(@PathVariable("accessToken") String accessToken) {
    saleManageCommandExecutor.sendMailWithAccessToken(accessToken);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @PostMapping(value = "/upload")
  public ResponseEntity uploadFile(MultipartFile[] files, @AuthenticationPrincipal User user) {
    saleManageCommandExecutor.fileUpload(files, user);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @PostMapping(value = "/download")
  public ResponseEntity getSaleListWithUser(
      @Valid @RequestBody DownloadRequestDTO dto, @AuthenticationPrincipal User user) {
    ;
    return ResponseEntity.status(HttpStatus.OK)
        .body(saleManageQueryProcessor.getSalesWithUserRole(dto, user));
  }

  @PostMapping(value = "/download/all")
  public ResponseEntity getAllSaleList(
      @Valid @RequestBody DownloadRequestDTO dto, @AuthenticationPrincipal User user) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(saleManageQueryProcessor.getAllSales(dto, user));
  }

  @GetMapping(value = "/sale")
  public ResponseEntity getSale(@RequestParam("accessToken") String accessToken) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(saleManageQueryProcessor.getSaleWithAccessToken(accessToken));
  }

  @PutMapping(value = "{saleManageId}/sale/{accessToken}")
  public ResponseEntity updateSale(
      @PathVariable("saleManageId") Long saleManageId,
      @PathVariable("accessToken") String accessToken,
      @RequestBody UpdateSaleDTO dto) {
    saleManageCommandExecutor.updateSale(saleManageId, accessToken, dto);
    return ResponseEntity.status(HttpStatus.OK).build();
  }
}

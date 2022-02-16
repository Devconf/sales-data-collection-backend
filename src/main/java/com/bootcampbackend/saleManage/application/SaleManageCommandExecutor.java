package com.bootcampbackend.saleManage.application;

import com.bootcampbackend.saleManage.application.common.ExcelUtils;
import com.bootcampbackend.saleManage.application.common.SaleMangeMapper;
import com.bootcampbackend.saleManage.domain.Sale;
import com.bootcampbackend.saleManage.domain.SaleManage;
import com.bootcampbackend.saleManage.domain.SaleManageRepository;
import com.bootcampbackend.saleManage.domain.SaleRepository;
import com.bootcampbackend.saleManage.infra.EmailSender;
import com.bootcampbackend.user.domain.User;
import com.bootcampbackend.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class SaleManageCommandExecutor {

  private final UserRepository userRepository;
  private final SaleRepository saleRepository;
  private final SaleManageRepository saleManageRepository;
  private final EmailSender emailSender;
  private final ExcelUtils excelUtils;
  private final SaleMangeMapper saleMangeMapper;

  @Transactional
  public void sendMail(long id) {
    User findUser =
        userRepository
            .findUserById(id)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자 입니다."));

    emailSender.sendEmail(findUser.getEmail());
    log.info("성공");
  }

  @Transactional
  public void sendMailAll() {
    userRepository
        .findAll()
        .forEach(
            user -> {
              emailSender.sendEmail(user.getEmail());
              log.info("성공");
            });
  }

  @Transactional
  public void sendMailWithAccessToken(String accessToken) {
    Sale findSale =
        saleRepository
            .findSaleByAccessToken(accessToken)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회사 입니다."));

    // todo 초대 메일을 발송한다.
    log.info("성공");
  }

  @Transactional
  public void fileUpload(MultipartFile[] files, User user) {
    try {

      SaleManage saleManage = new SaleManage(user);

      List<Workbook> workbookList = excelUtils.openExcelFile(files);

      // 우선 엑셀파일의 모든 Row를 다 Sale로 저장 하고 각각의 Sale마다 accessToken을 발행하여 임시 사용자가 정보를 수정할 수 있게 한다.
      // 만약 Sale에 중복된 데이터 발견시 임시 사용자는 accessToken을 가지고 수정이나 삭제 할 수 있다.
      for (Workbook workbook : workbookList) {
        Sheet sheet = workbook.getSheetAt(0);
        int lastRow = sheet.getLastRowNum();

        for (int i = 1; i <= lastRow; i++) {
          Sale sale =
              saleMangeMapper.toSale(
                  sheet.getRow(i).getCell(0).getStringCellValue(),
                  sheet.getRow(i).getCell(1).getStringCellValue(),
                  sheet.getRow(i).getCell(2).getStringCellValue(),
                  (int) sheet.getRow(i).getCell(3).getNumericCellValue(),
                  (int) sheet.getRow(i).getCell(4).getNumericCellValue(),
                  (int) sheet.getRow(i).getCell(5).getNumericCellValue(),
                  sheet.getRow(i).getCell(6).getDateCellValue().toString());

          saleManage.addSale(sale);
        }
      }

      saleManageRepository.save(saleManage);
    } catch (Exception e) {
      throw new IllegalArgumentException(e.getMessage());
    }
  }
}

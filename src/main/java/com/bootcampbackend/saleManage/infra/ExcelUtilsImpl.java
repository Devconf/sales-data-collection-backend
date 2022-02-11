package com.bootcampbackend.saleManage.infra;

import com.bootcampbackend.saleManage.application.common.ExcelUtils;
import lombok.extern.log4j.Log4j2;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Component
public class ExcelUtilsImpl implements ExcelUtils {

  public ExcelUtilsImpl() {}

  @Override
  public List<Workbook> openExcelFile(MultipartFile[] files) throws Exception {

    List<Workbook> workbooks = new ArrayList<>();

    if (files.length <= 0) {
      throw new IllegalArgumentException("파일을 업로드 해주세요.");
    }

    for (MultipartFile file : files) {
      InputStream inputStream = file.getInputStream();
      if (file.isEmpty()) {
        throw new IllegalArgumentException("파일을 업로드 해주세요.");
      }
      String[] arr = file.getOriginalFilename().split("\\.");
      String fileExtensionName = arr[arr.length - 1];

      if (fileExtensionName.equals("xls")) {
        HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
        if (!validateWorkbook(workbook)) {
          throw new IllegalArgumentException("변경된 파일 입니다. 메일로 첨부된 파일을 올려주세요.");
        }
        workbooks.add(workbook);
      } else if (fileExtensionName.equals("xlsx")) {
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        if (!validateWorkbook(workbook)) {
          throw new IllegalArgumentException("변경된 파일 입니다. 메일로 첨부된 파일을 올려주세요.");
        }
        workbooks.add(workbook);
      }
    }
    return workbooks;
  }

  private boolean validateWorkbook(Workbook workbook) {
    Sheet sheet = workbook.getSheetAt(0);
    Row header = sheet.getRow(0);
    if (header.getLastCellNum() != 6) {
      return false;
    }

    System.out.println(header.getCell(0).getRichStringCellValue());

    if (!header.getCell(0).getStringCellValue().equals("회사명")) {
      return false;
    }
    if (!header.getCell(1).getStringCellValue().equals("사업자번호")) {
      return false;
    }
    if (!header.getCell(2).getStringCellValue().equals("매출액")) {
      return false;
    }
    if (!header.getCell(3).getStringCellValue().equals("영업이익")) {
      return false;
    }
    if (!header.getCell(4).getStringCellValue().equals("당기순이익")) {
      return false;
    }
    if (!header.getCell(5).getStringCellValue().equals("기준일자(년월)")) {
      return false;
    }
    return true;
  }
}

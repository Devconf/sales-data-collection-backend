package com.bootcampbackend.saleManage.application.common;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ExcelUtils {

  public List<Workbook> openExcelFile(MultipartFile[] files) throws Exception;
}

package com.bootcampbackend.saleManage.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DownloadRequestDTO {
  @NotNull(message = "startAt 필드가 존재하지 않습니다.")
  @DateTimeFormat(pattern = "yyy-MM-dd")
  private LocalDate startAt;

  @NotNull(message = "endAt 필드가 존재하지 않습니다.")
  @DateTimeFormat(pattern = "yyy-MM-dd")
  private LocalDate endAt;
}

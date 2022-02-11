package com.bootcampbackend.saleManage.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Getter
@AllArgsConstructor
@Data
public class PageRequestDTO {

  private int page;
  private int size;

  public PageRequestDTO() {
    this.page = 1;
    this.size = 10;
  }

  public Pageable getPageable() {
    return PageRequest.of(page - 1, size);
  }
}

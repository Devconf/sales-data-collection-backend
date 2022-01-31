package com.bootcampbackend.sale.application;

import com.bootcampbackend.sale.application.common.SaleMapper;
import com.bootcampbackend.sale.application.dto.request.PageRequestDTO;
import com.bootcampbackend.sale.application.dto.response.GetAllUserDTO;
import com.bootcampbackend.user.application.UserQueryProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaleQueryProcessor {

  private final UserQueryProcessor userQueryProcessor;
  private final SaleMapper saleMapper;

  public GetAllUserDTO getUserList(PageRequestDTO pageRequest) {

    Pageable pageable = pageRequest.getPageable();

    return saleMapper.toUserDtoList(userQueryProcessor.getUserWidthPageable(pageable));
  }
}

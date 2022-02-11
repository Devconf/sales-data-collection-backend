package com.bootcampbackend.saleManage.application;

import com.bootcampbackend.saleManage.application.common.SaleMangeMapper;
import com.bootcampbackend.saleManage.application.dto.request.PageRequestDTO;
import com.bootcampbackend.saleManage.application.dto.response.GetAllUserDTO;
import com.bootcampbackend.user.application.UserQueryProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaleQueryProcessor {

  private final UserQueryProcessor userQueryProcessor;
  private final SaleMangeMapper saleMangeMapper;

  public GetAllUserDTO getUserList(PageRequestDTO pageRequest) {

    Pageable pageable = pageRequest.getPageable();

    return saleMangeMapper.toUserDtoList(userQueryProcessor.getUserListWidthPageable(pageable));
  }
}

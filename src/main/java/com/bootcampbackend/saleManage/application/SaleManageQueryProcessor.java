package com.bootcampbackend.saleManage.application;

import com.bootcampbackend.saleManage.application.common.SaleMangeMapper;
import com.bootcampbackend.saleManage.application.dto.request.DownloadRequestDTO;
import com.bootcampbackend.saleManage.application.dto.request.PageRequestDTO;
import com.bootcampbackend.saleManage.application.dto.response.GetAllUserDTO;
import com.bootcampbackend.saleManage.application.dto.response.GetSaleDTO;
import com.bootcampbackend.saleManage.domain.Sale;
import com.bootcampbackend.saleManage.domain.SaleRepository;
import com.bootcampbackend.user.application.UserQueryProcessor;
import com.bootcampbackend.user.domain.RoleType;
import com.bootcampbackend.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class SaleManageQueryProcessor {

  private final UserQueryProcessor userQueryProcessor;
  private final SaleRepository saleRepository;
  private final SaleMangeMapper saleMangeMapper;

  public GetAllUserDTO getUserList(PageRequestDTO pageRequest) {

    Pageable pageable = pageRequest.getPageable();

    return saleMangeMapper.toUserDtoList(userQueryProcessor.getUserListWidthPageable(pageable));
  }

  public List<GetSaleDTO> getSalesWithUserRole(DownloadRequestDTO dto, User user) {
    // 기간 선택후 조회
    log.info(dto.getStartAt());
    log.info(dto.getEndAt());

    return saleRepository
        .findSaleByUserIdAndBetweenDate(user.getId(), dto.getStartAt(), dto.getEndAt())
        .stream()
        .map(saleMangeMapper::toGetSaleDto)
        .collect(Collectors.toList());
  }

  public List<GetSaleDTO> getAllSales(DownloadRequestDTO dto, User user) {
    // 기간 선택후 조회
    if (user.getRole() != RoleType.ADMIN) {
      throw new IllegalArgumentException("관리자 전용입니다.");
    }

    return saleRepository.findSaleBetweenDate(dto.getStartAt(), dto.getEndAt()).stream()
        .map(saleMangeMapper::toGetSaleDto)
        .collect(Collectors.toList());
  }

  public GetSaleDTO getSaleWithAccessToken(String accessToken) {
    Sale sale =
        saleRepository
            .findSaleByAccessToken(accessToken)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회사입니다."));

    return saleMangeMapper.toGetSaleDto(sale);
  }
}

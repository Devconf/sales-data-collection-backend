package com.bootcampbackend.sale.application;

import com.bootcampbackend.user.domain.User;
import com.bootcampbackend.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@RequiredArgsConstructor
public class SaleCommandExecutor {

  private final UserRepository userRepository;

  @Transactional
  public void sendMail(long id) {
    User findUser =
        userRepository
            .findUserById(id)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자 입니다."));
    log.info("성공");
    // todo findUser.sendMail() 메일 전송 로직을 user에 추가한다.
  }

  @Transactional
  public void sendMailAll() {
    userRepository
        .findAll()
        .forEach(
            user -> {
              // todo user.sendMail()
              log.info("성공");
            });
  }
}

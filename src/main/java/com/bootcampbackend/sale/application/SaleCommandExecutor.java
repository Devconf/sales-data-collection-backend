package com.bootcampbackend.sale.application;

import com.bootcampbackend.sale.infra.EmailSender;
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
  private final EmailSender emailSender;

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
}

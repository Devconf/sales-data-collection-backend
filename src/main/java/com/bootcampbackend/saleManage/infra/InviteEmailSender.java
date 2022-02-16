package com.bootcampbackend.saleManage.infra;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;

@Log4j2
@Component
@RequiredArgsConstructor
public class InviteEmailSender {
  private final JavaMailSender javaMailSender;

  private String setFrom = "devconf5296@gmail.com"; // 보내는 이메일

  private String title = "매출액 관리"; // 메일 제목(생략 가능)

  public void invite(String toSendEmail, String companyName, String accessToken) {
    try {
      MimeMessage message = javaMailSender.createMimeMessage();
      MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");

      messageHelper.setFrom(this.setFrom);
      messageHelper.setTo(toSendEmail);
      messageHelper.setSubject(this.title);
      messageHelper.setText(this.getContent(companyName, accessToken));

      javaMailSender.send(message);
    } catch (Exception e) {
      log.info(e.getMessage());
      log.info("메시지 전송 오류");
    }
  }

  private String getContent(String companyName, String accessToken) {

    return "안녕하세요.\n"
        + companyName
        + "의 매출액 수정 또는 삭제 요청 부탁드립니다.\n\n\n"
        + "아래 링크에서 확인 부탁 드립니다.\n"
        + "http://localhost:3000/sale/"
        + accessToken;
  }
}

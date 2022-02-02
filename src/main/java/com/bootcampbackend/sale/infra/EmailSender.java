package com.bootcampbackend.sale.infra;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.activation.FileDataSource;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

@Log4j2
@Component
@RequiredArgsConstructor
public class EmailSender {
  private final JavaMailSender javaMailSender;

  private String setFrom = "devconf5296@gmail.com"; // 보내는 이메일

  private String title = "매출액 자료 요청"; // 메일 제목(생략 가능)

  private String content = "아래 첨부 파일 작성후 업로드 부탁드립니다."; // 메일 내용

  public void sendEmail(String toSendEmail) {
    try {
      String path = new ClassPathResource("매출액 업로드 첨부파일.xlsx").getURI().getPath().substring(1);

      MimeMessage message = javaMailSender.createMimeMessage();
      MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");

      messageHelper.setFrom(this.setFrom);
      messageHelper.setTo(toSendEmail);
      messageHelper.setSubject(this.title);
      messageHelper.setText(this.content);
      log.info(path);

      FileDataSource fds = new FileDataSource(path);

      String originalFileNm = "업로드 양식.xlsx"; // DB에 저장된 원본 파일명

      messageHelper.addAttachment(MimeUtility.encodeText(originalFileNm, "UTF-8", "B"), fds);

      javaMailSender.send(message);
    } catch (Exception e) {
      log.info(e.getMessage());
      log.info("메시지 전송 오류");
    }
  }
}

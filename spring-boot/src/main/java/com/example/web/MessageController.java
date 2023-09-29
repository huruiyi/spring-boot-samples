package com.example.web;

import java.util.Collections;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

@RestController
@Slf4j
public class MessageController {

  private final JavaMailSender javaMailSender;

  private final SpringTemplateEngine templateEngine;

  public MessageController(JavaMailSender javaMailSender, SpringTemplateEngine templateEngine) {
    this.javaMailSender = javaMailSender;
    this.templateEngine = templateEngine;
  }

  @GetMapping("/send1")
  public void send1() {
    javaMailSender.send(mimeMessage -> {
      MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
      helper.setTo("38761770@qq.com");
      helper.setFrom("807776962@qq.com");
      helper.setSubject("Status message-send1");
      helper.setText("All is well.");
    });
  }


  @GetMapping("/send2")
  public void send2() {
    javaMailSender.send(mimeMessage -> {
      MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
      helper.setTo("38761770@qq.com");
      helper.setFrom("807776962@qq.com");
      helper.setSubject("Status message-send2");

      Context context = new Context(LocaleContextHolder.getLocale(), Collections.singletonMap("msg", "All is well!"));
      String body = templateEngine.process("email.html", context);
      helper.setText(body, true);
    });
  }


}


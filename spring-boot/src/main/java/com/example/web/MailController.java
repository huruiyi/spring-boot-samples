package com.example.web;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

/**
 * 日常邮件联调接口，与启动时 {@code ApplicationRunner} 发送的启动通知区分开。
 */
@RestController
@RequestMapping("/mail")
public class MailController {

  private final JavaMailSender javaMailSender;
  private final SpringTemplateEngine templateEngine;

  @Value("${spring.mail.username:807776962@qq.com}")
  private String mailFrom;

  public MailController(JavaMailSender javaMailSender, SpringTemplateEngine templateEngine) {
    this.javaMailSender = javaMailSender;
    this.templateEngine = templateEngine;
  }

  /**
   * 日常测试：纯文本邮件。
   */
  @GetMapping("/send1")
  public Map<String, Object> send1() {
    String sentAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    String subject = "【日常测试·纯文本】MailController 联调";
    String text = "这是 MailController /mail/send1 触发的纯文本测试邮件。\n"
        + "用途：日常验证 SMTP 连通性。\n"
        + "说明：与项目启动通知无关。\n"
        + "发送时间：" + sentAt + "\n";

    javaMailSender.send(mimeMessage -> {
      MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
      helper.setTo("38761770@qq.com");
      helper.setFrom(mailFrom);
      helper.setSubject(subject);
      helper.setText(text, false);
    });

    Map<String, Object> result = new LinkedHashMap<>();
    result.put("ok", true);
    result.put("type", "plain-text");
    result.put("endpoint", "/mail/send1");
    result.put("subject", subject);
    result.put("sentAt", sentAt);
    return result;
  }

  /**
   * 日常测试：HTML 模板邮件。
   */
  @GetMapping("/send2")
  public Map<String, Object> send2() {
    String sentAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    String subject = "【日常测试·HTML】MailController 联调";

    Context context = new Context(LocaleContextHolder.getLocale());
    context.setVariable("msg", "HTML 模板渲染正常，SMTP 发送通道可用。");
    context.setVariable("sentAt", sentAt);
    context.setVariable("endpoint", "/mail/send2");
    String body = templateEngine.process("email-test.html", context);

    javaMailSender.send(mimeMessage -> {
      MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
      helper.setTo("38761770@qq.com");
      helper.setFrom(mailFrom);
      helper.setSubject(subject);
      helper.setText(body, true);
    });

    Map<String, Object> result = new LinkedHashMap<>();
    result.put("ok", true);
    result.put("type", "html");
    result.put("endpoint", "/mail/send2");
    result.put("template", "email-test.html");
    result.put("subject", subject);
    result.put("sentAt", sentAt);
    return result;
  }

}

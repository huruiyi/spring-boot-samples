package com.example.web;

import com.example.annotation.SysLog;
import com.example.enums.HttpStatusCode;
import com.example.exception.BusinessException;
import com.example.model.SampleDto;
import com.example.service.impl.CurrencyService;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Slf4j
@RestController
public class HomeController {

  @Value("${server.port}")
  private String port;

  private final JavaMailSender javaMailSender;
  private final SpringTemplateEngine templateEngine;
  private final CurrencyService currencyService;

  public HomeController(JavaMailSender javaMailSender, SpringTemplateEngine templateEngine, CurrencyService currencyService) {
    this.javaMailSender = javaMailSender;
    this.templateEngine = templateEngine;
    this.currencyService = currencyService;
  }

  @SysLog
  @GetMapping("/")
  public String index() {
    return "<p style='color:red;text-align:center;margin-top:20%;'>Hello World！世界你好！！</p>";
  }

  @RequestMapping("/sayHello")
  public String sayHello(String name) {
    return "hello " + name;
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

  @GetMapping("/session-set")
  public String set(HttpSession session) {
    session.setAttribute("user", "fairy");
    return String.valueOf(port);
  }

  @GetMapping("/session-get")
  public String get(HttpSession session) {
    return session.getAttribute("user") + ":" + port;
  }


  @PostMapping("/date-set")
  public SampleDto create(@RequestBody SampleDto payload) {
    return payload;
  }

  @GetMapping("/date-get")
  public SampleDto get() {
    final SampleDto dto = new SampleDto();
    final Instant time = Instant.ofEpochMilli(1571884105000L);
    dto.setInstant(time);
    dto.setDate(new Date(time.toEpochMilli()));
    dto.setLocalDate(time.atZone(ZoneId.of("UTC")).toLocalDate());
    dto.setLocalDateTime(time.atZone(ZoneId.of("UTC")).toLocalDateTime());
    return dto;
  }


  /**
   * <a href="http://localhost:9102/test1?i=0">...</a>
   * <p>
   * { "code": 600, "message": "自定义业务错误" }
   */
  @RequestMapping("/test1")
  public String test1(@RequestParam("i") int i) {
    if (i == 0) {
      throw new BusinessException(HttpStatusCode.INTERNAL_SERVER_ERROR, "除零异常");
    }
    return "success";
  }

  /**
   * <a href="http://localhost:9102/test2?i=0">...</a>
   * <p>
   * { "message": "自定义异常信息：/ by zero" }
   */
  @RequestMapping("/test2")
  public String test2(@RequestParam("i") int i) {
    log.info(String.valueOf(121 / i));
    return "success";
  }

  //返回 错误信息：test3.........
  @RequestMapping("/test3_1")
  @ResponseStatus(code = HttpStatus.OK, reason = "test3.........")
  public String test31() {
    return "hello world";
  }

  //正常返回，输出：hello world
  @RequestMapping("/test3_2")
  @ResponseStatus(code = HttpStatus.OK)
  public String test32() {
    return "hello world";
  }

  @RequestMapping("/currency-configuration")
  public String currencyService() {
    return currencyService.toString();
  }


}

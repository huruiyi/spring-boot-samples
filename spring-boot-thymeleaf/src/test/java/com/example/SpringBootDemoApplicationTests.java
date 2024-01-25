package com.example;

import com.example.bean.UserMailChangedDTO;
import com.example.bean.UserMailDTO;
import com.example.utils.Html2Pdf;
import com.lowagie.text.DocumentException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

@SpringBootTest
class SpringBootDemoApplicationTests {

  @Autowired
  private SpringTemplateEngine templateEngine;

  @Autowired
  private JavaMailSender javaMailSender;
  static Random random;

  @BeforeAll
  static void init() {
    random = new Random();
  }


  private Boolean getRandom() {
    return random.nextBoolean();
  }

  @Test
  void createUserTest() {
    System.out.println(javaMailSender.toString());
    List<UserMailDTO> userInfoList = new ArrayList<>();
    UserMailDTO userInfo;
    for (int i = 1; i < 10; i++) {
      userInfo = new UserMailDTO("AcNm", "ac@qq.com", "ccc(Ca/PA)", "Eliecer", "", null);
      userInfo.setSamAccountName(userInfo.getSamAccountName() + "-" + i);
      userInfoList.add(userInfo);
    }
    javaMailSender.send((msg) -> {
      MimeMessageHelper helper = new MimeMessageHelper(msg);
      helper.setTo("38761770@qq.com,1226603@qq.com".split(","));
      helper.setFrom("807776962@qq.com");
      helper.setSubject("Create User");

      Context context = new Context();
      context.setVariable("users", userInfoList);

      String body = templateEngine.process("email/create.html", context);
      helper.setText(body, true);
    });
  }

  @Test
  void updateUserTest() {
    System.out.println(javaMailSender.toString());
    List<UserMailDTO> userInfoList = new ArrayList<>();
    UserMailDTO userInfo;
    for (int i = 1; i < 10; i++) {
      UserMailChangedDTO changeUser = new UserMailChangedDTO(getRandom(), getRandom(), getRandom(), getRandom(), getRandom());
      userInfo = new UserMailDTO("AcNm", "ac@qq.com", "fl", "ln", "dn", "oc", changeUser);
      userInfo.setSamAccountName(userInfo.getSamAccountName() + "-" + i);
      userInfoList.add(userInfo);
    }
    javaMailSender.send((msg) -> {
      MimeMessageHelper helper = new MimeMessageHelper(msg);
      helper.setTo("38761770@qq.com");
      helper.setFrom("807776962@qq.com");
      helper.setSubject("Update User");

      Context context = new Context();
      context.setVariable("users", userInfoList);

      String body = templateEngine.process("email/update.html", context);
      helper.setText(body, true);
    });
  }

  @Test
  void deleteUserTest() {
    System.out.println(javaMailSender.toString());
    List<UserMailDTO> userInfoList = new ArrayList<>();
    UserMailDTO userInfo;
    for (int i = 1; i < 10; i++) {
      userInfo = new UserMailDTO("AcNm", "ac@qq.com", "dn", "oc");
      userInfo.setSamAccountName(userInfo.getSamAccountName() + "-" + i);
      userInfoList.add(userInfo);
    }
    javaMailSender.send((msg) -> {
      MimeMessageHelper helper = new MimeMessageHelper(msg);
      helper.setTo("38761770@qq.com");
      helper.setFrom("807776962@qq.com");
      helper.setSubject("Delete User");

      Context context = new Context();
      context.setVariable("users", userInfoList);

      String body = templateEngine.process("email/delete.html", context);
      helper.setText(body, true);
    });
  }

  @Test
  void sendMailWithPdf() throws FileNotFoundException {
    Context context = new Context(LocaleContextHolder.getLocale());
    String htmlBody = templateEngine.process("pdf/style.html", context);
    System.out.println(htmlBody);

    File dest = Paths.get("htmlBody.pdf").toFile();

    OutputStream os = new FileOutputStream(dest);
    ITextRenderer renderer = new ITextRenderer();
    ITextFontResolver fontResolver = renderer.getFontResolver();
    // 必须添加能支持中文的字体，否则html内容有中文会不显示，同时body标签要设置font-family: SimSun

    renderer.setDocumentFromString(htmlBody);
    renderer.layout();
    renderer.createPDF(os);
  }

  @Test
  void Test() throws IOException, DocumentException {
    List<UserMailDTO> userInfoList = new ArrayList<>();
    UserMailDTO userInfo;
    for (int i = 1; i < 10; i++) {
      userInfo = new UserMailDTO("li", "ac@qq.com", "dn", "oc");
      userInfo.setSamAccountName(userInfo.getSamAccountName() + "-" + i);
      userInfoList.add(userInfo);
    }

    Context context = new Context(LocaleContextHolder.getLocale());
    context.setVariable("users", userInfoList);
    context.setVariable("address", "add");

    String body = templateEngine.process("email/delete.html", context);

    FileOutputStream outputStream = new FileOutputStream("pdf-style-1.pdf");
    Html2Pdf.generator(outputStream, body);

    outputStream.close();
  }

}

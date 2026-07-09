package com.example.web;

import com.example.utils.ExcelUtils;
import com.example.utils.VerifyCodeUtils;
import com.example.utils.vcode.Captcha;
import com.example.utils.vcode.GifCaptcha;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/file")
public class FileController {

  @RequestMapping(value = "/exportExcel")
  public void test(HttpServletResponse response) {
    Map<String, String> params = new HashMap<>();
    params.put("userId", "1");
    log.info(params.toString());

    try (XSSFWorkbook xssfWorkbook = ExcelUtils.getOutputStream()) {
      try {
        SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddhhmmss");
        String fileName = sd.format(new Date()) + "预付款明细.xlsx";
        OutputStream output = response.getOutputStream();
        response.reset();
        response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
        response.setContentType("application/msexcel");
        xssfWorkbook.write(output);
        output.close();
      } catch (IOException e) {
        log.error(e.getMessage());
      }
    } catch (IOException e) {
      log.error(e.getMessage());
    }
  }

  @RequestMapping("/sVCode")
  public void createCertCodeImageAction(HttpServletRequest request, HttpServletResponse response) {
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-store");
    response.setDateHeader("Expires", 0);
    response.setContentType("image/jpeg");
    String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
    HttpSession session = request.getSession(true);
    session.setAttribute("v-code", verifyCode.toLowerCase());
    int w = 108, h = 34;
    try {
      VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);
    } catch (IOException e) {
      log.warn(e.getMessage());
    }
  }

  @RequestMapping("/dVCode")
  public void code2(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);
    response.setContentType("image/gif");
    Captcha captcha = new GifCaptcha(108, 34, 4);
    ServletOutputStream out = response.getOutputStream();
    captcha.out(out);
    out.flush();
    log.debug(captcha.text().toLowerCase());
    HttpSession session = request.getSession(true);
    session.setAttribute("v-code", captcha.text().toLowerCase());
  }

  @RequestMapping("/help")
  public void inHelpPage(HttpServletResponse response) throws IOException {
    response.reset();
    response.setContentType("application/pdf");
    response.setHeader("Content-disposition", "filename=help.pdf");
    Resource resource = new DefaultResourceLoader().getResource("classpath:pdf/java.pdf");
    File file = resource.getFile();
    try {
      if (file.exists()) {
        FileInputStream in = new FileInputStream(file);
        IOUtils.copy(in, response.getOutputStream());
        in.close();
      } else {
        log.debug("{} 文件不存在!", file);
      }
    } catch (IOException e) {
      log.debug("预览异常 {} ", e.getMessage());
    }
  }
}

package com.example.component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

@ControllerAdvice
public class XSSRequestBodyAdvice implements RequestBodyAdvice {

  @Override
  public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
    return true;
  }

  public static String filter(String input) {
    if (input == null) {
      return null;
    }
    return Jsoup.clean(input, Safelist.relaxed());
  }

  public static byte[] inputStreamToByteArray(InputStream inputStream) throws IOException {
    ByteArrayOutputStream buffer = new ByteArrayOutputStream();

    int nRead;
    byte[] data = new byte[1024];

    while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
      buffer.write(data, 0, nRead);
    }

    buffer.flush();
    return buffer.toByteArray();
  }

  @Override
  public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
      Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
    try {
      byte[] body = inputStreamToByteArray(inputMessage.getBody());
      String bodyString = new String(body, StandardCharsets.UTF_8);
      String filteredBody = filter(bodyString);
      InputStream filteredInputStream = new ByteArrayInputStream(filteredBody.getBytes(StandardCharsets.UTF_8));
      return new HttpInputMessage() {
        @Override
        public InputStream getBody() {
          return filteredInputStream;
        }

        @Override
        public org.springframework.http.HttpHeaders getHeaders() {
          return inputMessage.getHeaders();
        }
      };
    } catch (IOException e) {
      throw new IOException("Error processing request body", e);
    }
  }

  @Override
  public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
      Class<? extends HttpMessageConverter<?>> converterType) {
    return body;
  }

  @Override
  public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
      Class<? extends HttpMessageConverter<?>> converterType) {
    return body;
  }
}

package com.example.exception;

import com.example.enums.HttpStatusCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "600")
public class BusinessException extends RuntimeException {

  //自定义错误码
  public HttpStatusCode statusCode;

  //自定义构造器，必须输入错误码及内容
  public BusinessException(HttpStatusCode code, String msg) {
    super(msg);
    this.statusCode = code;
  }

  public HttpStatusCode getCode() {
    return statusCode;
  }

  public void setCode(HttpStatusCode code) {
    this.statusCode = code;
  }
}

package com.example.web;

import com.example.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExceptionController {

  /**
   * http://localhost:9102/test1?i=0
   * <p>
   * { "code": 600, "message": "自定义业务错误" }
   *
   * @param i
   * @return
   */
  @RequestMapping("/test1")
  public String test1(@RequestParam("i") int i) {
    if (i == 0) {
      throw new BusinessException(600, "自定义业务错误");
    }
    return "success";
  }


  /**
   * http://localhost:9102/test2?i=0
   * <p>
   * { "message": "自定义异常信息：/ by zero" }
   *
   * @param i
   * @return
   */
  @RequestMapping("/test2")
  public String test2(@RequestParam("i") int i) {
    int x = 121 / i;
    return "success";
  }

  //返回 错误信息：test3.........
  @RequestMapping("/test3_1")
  @ResponseStatus(code = HttpStatus.OK, reason = "test3.........")
  public String test3_1() {
    return "hello world";
  }

  //正常返回，输出：hello world
  @RequestMapping("/test3_2")
  @ResponseStatus(code = HttpStatus.OK)
  public String test3_2() {
    return "hello world";
  }
}

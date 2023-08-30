package com.example.web;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.example.entity.User;
import com.example.repository.UserRepository;
import com.example.service.UserService;
import com.example.utils.Response;
import java.util.List;
import java.util.Optional;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(value = "user")
public class UserController {

  private final UserRepository userRepository;

  private final UserService userService;
  final MessageSource messageSource;

  public UserController(UserRepository userRepository, UserService userService, MessageSource messageSource) {
    this.userRepository = userRepository;
    this.userService = userService;
    this.messageSource = messageSource;
  }

  @ResponseBody
  @PostMapping("register")
  public Response user(User user) {
    Response res = new Response();
    try {
      user.setDisabled(false);
      List<User> users = userRepository.findByName(user.getName());
      if (users.isEmpty()) {
        user.setPassword(DigestUtils.md5Hex(user.getPassword()));
        userRepository.save(user);
        res.setFlag(true);
        res.setMessage("用戶注冊成功");
      } else {
        res.setFlag(true);
        res.setMessage("用戶已注册");
      }
    } catch (Exception ex) {
      res.setFlag(false);
      res.setMessage("系统异常..");
    }
    return res;
  }

  @PostMapping("get")
  @ResponseBody
  public User change(String name) {
    List<User> users = userRepository.findByName(name);
    if (!users.isEmpty()) {
      return users.get(0);
    } else {
      return null;
    }
  }

  @PostMapping("change")
  @ResponseBody
  public Response change(User user) {
    Response res = new Response();
    try {
      List<User> users = userRepository.findByName(user.getName());
      if (!users.isEmpty()) {
        User userInfo = users.get(0);
        userInfo.setSex(user.getSex());
        userInfo.setEmail(user.getEmail());
        userInfo.setTelPhone(user.getTelPhone());
        userInfo.setpSign(user.getpSign());
        userRepository.save(userInfo);
        res.setFlag(true);
        res.setMessage("修改成功");
      } else {
        res.setMessage("修改失敗");
      }
      return res;
    } catch (Exception ex) {
      res.setFlag(false);
      res.setMessage("系统异常..");
      return res;
    }
  }

  @RequestMapping(value = "/all", method = RequestMethod.GET)
  public List<User> getAllUsers() {
    return this.userService.getAllUsers();
  }

  @RequestMapping(value = "/adduser", method = RequestMethod.POST,
      consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
  public User addUser(@RequestBody User user) {
    return this.userService.addUser(user);
  }

  @RequestMapping(value = "/updateuser", method = RequestMethod.PUT,
      consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
  public User updateUser(@RequestBody User user) {
    return this.userService.updateUser(user);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public Optional<User> getUser(@PathVariable int id) {
    return this.userService.getUserById(id);
  }

  @RequestMapping(value = "/all", method = RequestMethod.DELETE)
  public void deleteAllUsers() {
    this.userService.deleteAllUsers();
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public void deleteUser(@PathVariable int id) {
    this.userService.deleteUserById(id);
  }

  /**
   * <a href="http://localhost:8034/user/get-greeting?language=fr">fr greeting</a>
   * <a href="http://localhost:8034/user/get-greeting?language=en">en greeting</a>
   */
  @RequestMapping(value = "/get-greeting", method = RequestMethod.GET)
  public String greeting() {
    return messageSource.getMessage("good.morning", null, LocaleContextHolder.getLocale());
  }

  @RequestMapping(value = "/get-greeting-name", method = RequestMethod.GET)
  public String greetingWithName() {
    String[] params = new String[]{"Ikhiloya", "today"};
    return messageSource.getMessage("good.morning.name", params, LocaleContextHolder.getLocale());
  }

}

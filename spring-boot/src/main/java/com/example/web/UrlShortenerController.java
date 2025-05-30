package com.example.web;

import com.google.common.hash.Hashing;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/rest/url")
public class UrlShortenerController {

  @Autowired
  StringRedisTemplate redisTemplate;

  @GetMapping("/{id}")
  public String getUrl(@PathVariable String id) {
    String url = redisTemplate.opsForValue().get(id);
    if (url == null) {
      throw new RuntimeException("There is no shorter URL for : " + id);
    }
    return url;
  }

  /**
   * curl --location 'https://192.168.0.110/rest/url' \
   * --header 'Content-Type: text/plain' \
   * --data 'https://micronaut.io/launch'
   */
  @PostMapping
  public String create(@RequestBody String url) {
    UrlValidator urlValidator = new UrlValidator(new String[]{"http", "https"});
    if (urlValidator.isValid(url)) {
      String id = Hashing.murmur3_32_fixed().hashString(url, StandardCharsets.UTF_8).toString();
      log.info("URL Id generated: {}", id);
      redisTemplate.opsForValue().set(id, url, 1, TimeUnit.HOURS);
      return id;
    }
    throw new RuntimeException("URL Invalid: " + url);
  }
}

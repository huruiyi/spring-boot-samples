package com.example.web;

import com.example.enums.HttpStatusCode;
import com.example.exception.BusinessException;
import com.example.utils.PathUtils;
import com.google.common.hash.Hashing;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 短链接 Demo。
 * <p>
 * 使用流程：
 * <ol>
 *   <li>{@code POST /rest/url} 提交长链接，拿到 {@code shortUrl}</li>
 *   <li>浏览器或客户端访问 {@code shortUrl}（即 {@code GET /s/{code}}）</li>
 *   <li>服务端 302 跳转到原始长链接</li>
 * </ol>
 * {@code GET /rest/url/{code}} 仅查询映射，不跳转，便于联调。
 */
@Slf4j
@RestController
public class UrlShortenerController {

  private static final String REDIS_KEY_PREFIX = "url:short:";
  private static final long TTL_HOURS = 1L;
  private static final UrlValidator URL_VALIDATOR = new UrlValidator(new String[]{"http", "https"});

  private final StringRedisTemplate redisTemplate;

  public UrlShortenerController(StringRedisTemplate redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  /**
   * 创建短链接。
   * <pre>
   * curl -k -X POST 'https://localhost/rest/url' \
   *   -H 'Content-Type: text/plain' \
   *   -d 'https://spring.io/projects/spring-boot'
   * </pre>
   */
  @PostMapping("/rest/url")
  public Map<String, Object> create(@RequestBody String url, HttpServletRequest request) {
    String longUrl = normalizeUrl(url);
    if (!URL_VALIDATOR.isValid(longUrl)) {
      throw new BusinessException(HttpStatusCode.BAD_REQUEST, "URL Invalid: " + longUrl);
    }

    String code = shortCodeOf(longUrl);
    redisTemplate.opsForValue().set(redisKey(code), longUrl, TTL_HOURS, TimeUnit.HOURS);
    log.info("短链接已生成, code={}, url={}", code, longUrl);

    String shortUrl = buildShortUrl(request, code);
    Map<String, Object> result = new LinkedHashMap<>();
    result.put("code", code);
    result.put("shortUrl", shortUrl);
    result.put("originalUrl", longUrl);
    result.put("ttlHours", TTL_HOURS);
    result.put("usage", "浏览器打开 shortUrl，将 302 跳转到 originalUrl");
    return result;
  }

  /**
   * 短链接跳转入口（真正“使用短链接”的方式）。
   */
  @GetMapping("/s/{code}")
  public ResponseEntity<Void> redirect(@PathVariable String code) {
    String longUrl = getLongUrl(code);
    return ResponseEntity.status(HttpStatus.FOUND)
        .location(URI.create(longUrl))
        .header(HttpHeaders.CACHE_CONTROL, "no-cache")
        .build();
  }

  /**
   * 查询短码对应的长链接（不跳转，便于 API / 首页联调）。
   */
  @GetMapping("/rest/url/{code}")
  public Map<String, Object> resolve(@PathVariable String code, HttpServletRequest request) {
    String longUrl = getLongUrl(code);
    Long ttl = redisTemplate.getExpire(redisKey(code), TimeUnit.SECONDS);

    Map<String, Object> result = new LinkedHashMap<>();
    result.put("code", code);
    result.put("shortUrl", buildShortUrl(request, code));
    result.put("originalUrl", longUrl);
    result.put("ttlSeconds", ttl == null ? -1 : ttl);
    return result;
  }

  private String getLongUrl(String code) {
    if (!StringUtils.hasText(code)) {
      throw new BusinessException(HttpStatusCode.BAD_REQUEST, "短码不能为空");
    }
    String longUrl = redisTemplate.opsForValue().get(redisKey(code));
    if (longUrl == null) {
      throw new BusinessException(HttpStatusCode.NOT_FOUND, "短链接不存在或已过期: " + code);
    }
    return longUrl;
  }

  private static String normalizeUrl(String url) {
    return url == null ? "" : url.trim();
  }

  private static String shortCodeOf(String longUrl) {
    int hash = Hashing.murmur3_32_fixed().hashString(longUrl, StandardCharsets.UTF_8).asInt();
    return Integer.toUnsignedString(hash, 36);
  }

  private static String redisKey(String code) {
    return REDIS_KEY_PREFIX + code;
  }

  private static String buildShortUrl(HttpServletRequest request, String code) {
    return PathUtils.baseUrl(request) + "/s/" + code;
  }
}

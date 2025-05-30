package com.example.utils;

import com.google.common.hash.Hashing;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class UrlShortener {

  // 存储短链接到长链接的映射
  private final Map<String, String> shortToLong;
  // 存储长链接到短链接的映射，避免重复生成
  private final Map<String, String> longToShort;
  // 短链接服务的基础 URL
  private final String baseUrl;
  // 短码的长度
  private final int codeLength;
  // 随机数生成器
  private final Random random;
  // 允许的字符集
  private static final String ALLOWED_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

  public UrlShortener(String baseUrl, int codeLength) {
    this.shortToLong = new HashMap<>();
    this.longToShort = new HashMap<>();
    this.baseUrl = baseUrl;
    this.codeLength = codeLength;
    this.random = new Random();
  }

  /**
   * 生成短链接
   *
   * @param longUrl 原始长链接
   * @return 生成的短链接
   */
  public String shorten(String longUrl) {
    // 检查长链接是否已经存在
    if (longToShort.containsKey(longUrl)) {
      return baseUrl + "/" + longToShort.get(longUrl);
    }

    // 使用 Guava 的 Hashing 生成哈希值
    String hash = Hashing.murmur3_32_fixed()
        .hashString(longUrl, StandardCharsets.UTF_8)
        .toString();

    // 从哈希值生成短码
    String shortCode = generateShortCode(hash);

    // 处理哈希冲突
    while (shortToLong.containsKey(shortCode)) {
      shortCode = generateRandomShortCode();
    }

    // 保存映射关系
    shortToLong.put(shortCode, longUrl);
    longToShort.put(longUrl, shortCode);

    return baseUrl + "/" + shortCode;
  }

  /**
   * 根据短链接查找原始长链接
   *
   * @param shortUrl 短链接
   * @return 原始长链接，如果不存在则返回 null
   */
  public String expand(String shortUrl) {
    // 提取短码部分
    String shortCode = shortUrl.substring(baseUrl.length() + 1);
    return shortToLong.get(shortCode);
  }

  /**
   * 从哈希值生成短码
   */
  private String generateShortCode(String hash) {
    // 简单实现：从哈希字符串中截取固定长度的子串
    // 实际应用中可能需要更复杂的实现来减少冲突
    if (hash.length() <= codeLength) {
      return hash;
    }
    return hash.substring(0, codeLength);
  }

  /**
   * 生成随机短码，用于处理哈希冲突
   */
  private String generateRandomShortCode() {
    StringBuilder sb = new StringBuilder(codeLength);
    for (int i = 0; i < codeLength; i++) {
      int index = random.nextInt(ALLOWED_CHARS.length());
      sb.append(ALLOWED_CHARS.charAt(index));
    }
    return sb.toString();
  }

  public static void main(String[] args) {
    // 创建一个基础 URL 为 http://short.url 的短链接生成器
    UrlShortener shortener = new UrlShortener("http://short.url", 6);

    // 测试用的长链接
    String longUrl = "https://www.example.com/some/very/long/path/to/a/resource?param1=value1&param2=value2";

    // 生成短链接
    String shortUrl = shortener.shorten(longUrl);
    System.out.println("原始长链接: " + longUrl);
    System.out.println("生成的短链接: " + shortUrl);

    // 还原长链接
    String expandedUrl = shortener.expand(shortUrl);
    System.out.println("还原的长链接: " + expandedUrl);
  }
}

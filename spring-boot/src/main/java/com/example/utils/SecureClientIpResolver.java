package com.example.utils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 在反向代理场景下安全解析真实客户端 IP（JDK 8，无第三方 IP 库）。
 * <p>
 * 关键规则：仅当 TCP 对端（remoteAddr）属于可信代理时，才解析代理头；
 * 解析 {@code X-Forwarded-For} 时从右向左跳过可信代理，取第一个非可信代理 IP
 * （左侧可由客户端伪造，不可直接取最左值）。
 */
public class SecureClientIpResolver {

  private final List<Cidr> trustedProxies;

  public SecureClientIpResolver(List<String> trustedProxyCidrs) {
    this.trustedProxies = parseCidrs(trustedProxyCidrs);
  }

  public String resolve(HttpServletRequest request) {
    String remoteAddr = request.getRemoteAddr();
    InetAddress remote = parseIp(remoteAddr);
    if (remote == null) {
      return "invalid";
    }

    if (!isTrustedProxy(remote)) {
      return remote.getHostAddress();
    }

    String forwardedFor = request.getHeader("X-Forwarded-For");
    if (!isBlank(forwardedFor)) {
      String[] forwardedIps = forwardedFor.split(",");
      for (int i = forwardedIps.length - 1; i >= 0; i--) {
        InetAddress candidate = parseIp(forwardedIps[i].trim());
        if (candidate == null) {
          continue;
        }
        if (isTrustedProxy(candidate)) {
          continue;
        }
        return candidate.getHostAddress();
      }
    }

    String realIpHeader = request.getHeader("X-Real-IP");
    if (!isBlank(realIpHeader)) {
      InetAddress realIp = parseIp(realIpHeader.trim());
      if (realIp != null && !isTrustedProxy(realIp)) {
        return realIp.getHostAddress();
      }
    }

    // 可信代理未附带可用客户端 IP 时，退回 TCP 对端
    return remote.getHostAddress();
  }

  public boolean isTrustedProxy(String ip) {
    InetAddress address = parseIp(ip);
    return address != null && isTrustedProxy(address);
  }

  private boolean isTrustedProxy(InetAddress address) {
    for (Cidr trusted : trustedProxies) {
      if (trusted.contains(address)) {
        return true;
      }
    }
    return false;
  }

  /**
   * 仅解析字面量 IP，避免对主机名做 DNS 查询。
   */
  static InetAddress parseIp(String ip) {
    if (isBlank(ip)) {
      return null;
    }
    String value = stripZoneId(ip.trim());
    if (!looksLikeIpLiteral(value)) {
      return null;
    }
    try {
      return InetAddress.getByName(value);
    } catch (UnknownHostException e) {
      return null;
    }
  }

  private static List<Cidr> parseCidrs(List<String> cidrs) {
    if (cidrs == null || cidrs.isEmpty()) {
      return Collections.emptyList();
    }
    List<Cidr> parsed = new ArrayList<Cidr>();
    for (String cidr : cidrs) {
      Cidr network = Cidr.parse(cidr);
      if (network != null) {
        parsed.add(network);
      }
    }
    return Collections.unmodifiableList(parsed);
  }

  private static boolean isBlank(String value) {
    return value == null || value.trim().isEmpty();
  }

  /**
   * 去掉 IPv6 zone id，例如 fe80::1%eth0
   */
  private static String stripZoneId(String ip) {
    int percent = ip.indexOf('%');
    if (percent >= 0) {
      return ip.substring(0, percent);
    }
    return ip;
  }

  private static boolean looksLikeIpLiteral(String value) {
    if (value.indexOf(':') >= 0) {
      // 粗略判断 IPv6：仅含十六进制、冒号、点（IPv4-mapped）
      for (int i = 0; i < value.length(); i++) {
        char c = value.charAt(i);
        if (!(c == ':' || c == '.'
            || (c >= '0' && c <= '9')
            || (c >= 'a' && c <= 'f')
            || (c >= 'A' && c <= 'F'))) {
          return false;
        }
      }
      return true;
    }
    // IPv4：四段数字
    String[] parts = value.split("\\.");
    if (parts.length != 4) {
      return false;
    }
    for (String part : parts) {
      if (part.isEmpty() || part.length() > 3) {
        return false;
      }
      for (int i = 0; i < part.length(); i++) {
        if (!Character.isDigit(part.charAt(i))) {
          return false;
        }
      }
      int n = Integer.parseInt(part);
      if (n < 0 || n > 255) {
        return false;
      }
    }
    return true;
  }

  /**
   * CIDR 网段匹配（支持 IPv4 / IPv6）。
   */
  static final class Cidr {
    private final byte[] network;
    private final int prefixLength;

    private Cidr(byte[] network, int prefixLength) {
      this.network = network;
      this.prefixLength = prefixLength;
    }

    static Cidr parse(String cidr) {
      if (isBlank(cidr)) {
        return null;
      }
      String value = cidr.trim();
      String ipPart = value;
      Integer prefix = null;
      int slash = value.indexOf('/');
      if (slash >= 0) {
        ipPart = value.substring(0, slash);
        try {
          prefix = Integer.valueOf(value.substring(slash + 1));
        } catch (NumberFormatException e) {
          return null;
        }
      }

      InetAddress address = parseIp(ipPart);
      if (address == null) {
        return null;
      }
      byte[] bytes = address.getAddress();
      int maxPrefix = bytes.length * 8;
      int prefixLength = prefix == null ? maxPrefix : prefix.intValue();
      if (prefixLength < 0 || prefixLength > maxPrefix) {
        return null;
      }
      return new Cidr(bytes, prefixLength);
    }

    boolean contains(InetAddress address) {
      if (address == null) {
        return false;
      }
      byte[] candidate = address.getAddress();
      if (candidate.length != network.length) {
        return false;
      }
      int fullBytes = prefixLength / 8;
      int remainingBits = prefixLength % 8;
      for (int i = 0; i < fullBytes; i++) {
        if (candidate[i] != network[i]) {
          return false;
        }
      }
      if (remainingBits == 0) {
        return true;
      }
      int mask = 0xFF << (8 - remainingBits);
      return (candidate[fullBytes] & mask) == (network[fullBytes] & mask);
    }
  }

}
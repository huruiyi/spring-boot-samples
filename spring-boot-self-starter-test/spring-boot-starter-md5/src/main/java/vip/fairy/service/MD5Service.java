package vip.fairy.service;


import java.security.NoSuchAlgorithmException;
import vip.fairy.util.MD5Util;

public class MD5Service {

  public String getMD5(String input)   {
    return MD5Util.getMD5(input.getBytes());
  }

}

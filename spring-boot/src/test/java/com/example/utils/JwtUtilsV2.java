package com.example.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.Calendar;
import java.util.Map;

public class JwtUtilsV2 {

  private static final String SIGNATURE = "token!@#$%^7890";

  /**
   * 生成token
   * @param map //传入payload
   * @return 返回token
   */
  public static String getToken(Map<String,String> map){
    JWTCreator.Builder builder = JWT.create();
    map.forEach(builder::withClaim);
    Calendar instance = Calendar.getInstance();
    instance.add(Calendar.SECOND,7);
    builder.withExpiresAt(instance.getTime());
    return builder.sign(Algorithm.HMAC256(SIGNATURE));
  }

  /**
   * 验证token
   */
  public static void verify(String token){
    JWT.require(Algorithm.HMAC256(SIGNATURE)).build().verify(token);
  }

  /**
   * 获取token中payload
   */
  public static DecodedJWT getToken(String token){
    return JWT.require(Algorithm.HMAC256(SIGNATURE)).build().verify(token);
  }
}

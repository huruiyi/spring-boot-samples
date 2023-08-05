package com.example.Spring.utils;

import com.alibaba.fastjson2.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.Spring.jwt.RSA256Key;
import com.example.Spring.jwt.UserInfo;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Date;
import java.util.UUID;
import org.apache.commons.lang3.time.DateUtils;

public class JwtUtils {

  private static final String ISSUER = "WUYUWEI_BACK_API";

  public static String createToken(UserInfo user) throws Exception {
    RSA256Key rsa256Key = SecretKeyUtils.getRSA256Key(); // 获取公钥/私钥
    RSAPublicKey publicKey = rsa256Key.getPublicKey();
    RSAPrivateKey privateKey = rsa256Key.getPrivateKey();

    System.out.println(publicKey.getPublicExponent());
    System.out.println(privateKey.getPrivateExponent());

    Algorithm algorithm = Algorithm.RSA256(publicKey, privateKey);

    return createToken(algorithm, user);
  }

  public static String createToken(Algorithm algorithm, Object data) throws Exception {

    String[] audience = {"app", "web"};
    return JWT.create()
        .withIssuer(ISSUER)                      //发布者
        .withAudience(audience)                  //观众，相当于接受者
        .withIssuedAt(new Date())                // 生成签名的时间
        .withExpiresAt(DateUtils.addMinutes(new Date(), 5))
        .withClaim("data", JSON.toJSONString(data)) //存数据
        .withNotBefore(new Date())                   //生效时间
        .withJWTId(UUID.randomUUID().toString())    //编号
        .sign(algorithm);                           //签入
  }

  public static DecodedJWT verifierToken(String token) throws Exception {
    RSA256Key rsa256Key = SecretKeyUtils.getRSA256Key();
    RSAPublicKey publicKey = rsa256Key.getPublicKey();
    RSAPrivateKey privateKey = rsa256Key.getPrivateKey();

    System.out.println(publicKey.getPublicExponent());
    System.out.println(privateKey.getPrivateExponent());

    Algorithm algorithm = Algorithm.RSA256(publicKey, privateKey);
    JWTVerifier verifier = JWT.require(algorithm).withIssuer(ISSUER).build();
    return verifier.verify(token);
  }


  public static byte[] decryptBASE64(String data) throws Exception {
    Decoder decoder = Base64.getDecoder();
    return decoder.decode(data);
  }


  /**
   * String转公钥PublicKey
   */
  public static PublicKey getPublicKey(String key) throws Exception {
    byte[] keyBytes = decryptBASE64(key);
    X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    return keyFactory.generatePublic(keySpec);
  }

  /**
   * String转私钥PrivateKey
   */
  public static PrivateKey getPrivateKey(String key) throws Exception {
    byte[] keyBytes = decryptBASE64(key);
    PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    return keyFactory.generatePrivate(keySpec);
  }


}
package com.example.jwt;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import com.alibaba.fastjson2.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.utils.JwtUtils;
import com.example.utils.JwtUtilsV2;
import com.example.utils.PemUtils;
import java.nio.charset.StandardCharsets;
import java.security.interfaces.RSAKey;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DemoTests {

  private static final String ISSUER = "WUYUWEI_BACK_API";
  private static final String PUBLIC_KEY_FILE_RSA = "src/test/resources/rsa-public.pem";
  private static final String PRIVATE_KEY_FILE_RSA = "src/test/resources/rsa-private.pem";


  @Test
  void creatToken() {
    Algorithm algorithm = Algorithm.HMAC256("secret");
    String token = JWT.create()
        .withIssuer("auth0")
        .withIssuedAt(new Date())
        .withExpiresAt(DateUtils.addHours(new Date(), 2))
        .withClaim("name", "huruiyi") // 插入数据
        .withClaim("age", "31") // 插入数据
        .sign(algorithm);

    System.out.println(token);
    Assertions.assertNotNull(token);
  }

  @Test
  void verifierToken() {
    String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhdXRoMCIsImlhdCI6MTcyODcyMDMyOCwiZXhwIjoxNzI4NzI3NTI4LCJuYW1lIjoiaHVydWl5aSIsImFnZSI6IjMxIn0.xc5rPZa0WyV1w3RukQjseQhGqfGjQvgyFaHOpx-H2Xo";
    try {
      Algorithm algorithm = Algorithm.HMAC256("secret");
      JWTVerifier verifier = JWT.require(algorithm).withIssuer("auth0").build();
      DecodedJWT jwt = verifier.verify(token);

      System.out.println(jwt);

      System.out.println(jwt.getIssuer());

      System.out.println(jwt.getIssuedAt());

      System.out.println(jwt.getExpiresAt());

      Map<String, Claim> claims = jwt.getClaims();
      Claim claim1Name = claims.get("name");
      System.out.println(claim1Name.asString());

      Claim claim1Age = claims.get("age");
      System.out.println(claim1Age.asString());

      Claim claim2Name = jwt.getClaim("name");
      System.out.println(claim2Name.asString());

      Claim claim2Age = jwt.getClaim("age");
      System.out.println(claim2Age.asString());
    } catch (JWTVerificationException e) {
      System.out.println("666");
      e.printStackTrace();
    }
  }

  @Test
  void decodeToken() {
    String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhdXRoMCIsImlhdCI6MTcyODcyMDMyOCwiZXhwIjoxNzI4NzI3NTI4LCJuYW1lIjoiaHVydWl5aSIsImFnZSI6IjMxIn0.xc5rPZa0WyV1w3RukQjseQhGqfGjQvgyFaHOpx-H2Xo";

    DecodedJWT jwt = JWT.decode(token);

    String algorithm = jwt.getAlgorithm(); //获取算法类型
    String type = jwt.getType();         //获取token类型
    String issuer = jwt.getIssuer();     //获取token发布者
    Date expiresAt = jwt.getExpiresAt(); //获取token过期时间
    Date issuedAt = jwt.getIssuedAt();    // 获取token生产日期

    System.out.println(algorithm);  //=>    HS256
    System.out.println(type);       //=>    JWT
    System.out.println(issuer);     //=>    auth0
    System.out.println(expiresAt);  //=>    Sat Jan 11 22:25:13 CST 2020
    System.out.println(issuedAt);   //=>    Sat Jan 11 20:25:13 CST 2020
    Assertions.assertNotNull(jwt);
  }


  @Test
  void shouldCreateAnEmptyRSA256SignedToken() throws Exception {
    RSAKey rsa3 = (RSAKey) PemUtils.readPrivateKeyFromFile(PRIVATE_KEY_FILE_RSA, "RSA");
    Algorithm rsa2 = Algorithm.RSA256(rsa3);

    String[] audience = {"app", "web"};

    UserInfo userInfo = new UserInfo();
    userInfo.setUserName("admin");
    userInfo.setPassword("password");

    String data = JSON.toJSONString(userInfo);

    String signed = JWT.create()
        .withIssuer(ISSUER)                      //发布者
        .withAudience(audience)                  //观众，相当于接受者
        .withIssuedAt(new Date())                // 生成签名的时间
        .withExpiresAt(DateUtils.addMinutes(new Date(), 5))
        .withClaim("data", JSON.toJSONString(data)) //存数据
        .withNotBefore(new Date())                   //生效时间
        .withJWTId(UUID.randomUUID().toString())    //编号
        .sign(rsa2);

    System.out.println(signed);

    System.out.println("*****************************************************************************");
    String[] parts = signed.split("\\.");
    String headerJson = new String(Base64.getUrlDecoder().decode(parts[0]), StandardCharsets.UTF_8);
    System.out.println(headerJson);

    String payloadJson = new String(Base64.getUrlDecoder().decode(parts[1]), StandardCharsets.UTF_8);
    System.out.println(payloadJson);

    String signatureJson = new String(Base64.getUrlDecoder().decode(parts[2]), StandardCharsets.US_ASCII);
    System.out.println(signatureJson);
    System.out.println("*****************************************************************************");

    RSAKey rsa1 = (RSAKey) PemUtils.readPublicKeyFromFile(PUBLIC_KEY_FILE_RSA, "RSA");
    Algorithm rsa = Algorithm.RSA256(rsa1);
    JWTVerifier verified = JWT.require(rsa).withIssuer(ISSUER).build();
    DecodedJWT verify = verified.verify(signed);

    String token = verify.getToken();
    System.out.println(token);

    Map<String, Claim> claims = verify.getClaims();
    System.out.println(claims);
    Claim userInfoJson = verify.getClaim("data");
    System.out.println(userInfoJson);

    assertThat(verified, is(notNullValue()));
  }

  @Test
  void verifierTokenTest() throws Exception {
    UserInfo userInfo = new UserInfo();
    userInfo.setUserName("admin");
    userInfo.setPassword("password");
    String token = JwtUtils.createToken(userInfo);

    DecodedJWT decodedJWT = JwtUtils.verifierToken(token);
    System.out.println(JSON.toJSONString(decodedJWT));

    Claim data = decodedJWT.getClaim("data");
    System.out.println(data);

    Map<String, Claim> claims = decodedJWT.getClaims();
    System.out.println(claims);
  }

  @Test
  void v2Tet() {
    String token = "";
    Map<String, Object> map = new HashMap<>();
    try {
      UserInfo userInfo = new UserInfo(1000L, "admin", "password");
      Map<String, String> payload = new HashMap<>();
      payload.put("id", userInfo.getId().toString());
      payload.put("name", userInfo.getUserName());
      token = JwtUtilsV2.getToken(payload);
      map.put("state", true);
      map.put("msg", "认证成功");
      map.put("token", token);//响应token
    } catch (Exception e) {
      map.put("state", "false");
      map.put("msg", e.getMessage());
    }
    JwtUtilsV2.verify(token);
    System.out.println(map);
  }


}

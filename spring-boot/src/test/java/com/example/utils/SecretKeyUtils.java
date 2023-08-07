package com.example.utils;

import com.example.jwt.RSA256Key;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import java.util.HashMap;
import java.util.Map;


public class SecretKeyUtils {

  public static final String KEY_ALGORITHM = "RSA";
  private static final String PUBLIC_KEY = "RSAPublicKey";
  private static final String PRIVATE_KEY = "RSAPrivateKey";

  private static volatile RSA256Key rsa256Key;

  //获得公钥
  public static String getPublicKey(Map<String, Object> keyMap) {
    Key key = (Key) keyMap.get(PUBLIC_KEY);
    return encryptBASE64(key.getEncoded());
  }

  public static String getPublicKey(RSA256Key rsa256Key) {
    Key key = rsa256Key.getPublicKey();
    return encryptBASE64(key.getEncoded());
  }

  //获得私钥
  public static String getPrivateKey(Map<String, Object> keyMap) {
    Key key = (Key) keyMap.get(PRIVATE_KEY);
    return encryptBASE64(key.getEncoded());
  }

  //获得私钥
  public static String getPrivateKey(RSA256Key rsa256Key) {
    Key key = rsa256Key.getPrivateKey();
    return encryptBASE64(key.getEncoded());
  }


  /**
   * BASE64Encoder 加密
   *
   * @param data 要加密的数据
   * @return 加密后的字符串
   */
  public static String encryptBASE64(byte[] data) {
    Encoder encoder = Base64.getEncoder();
    return encoder.encodeToString(data);
  }

  /**
   * BASE64Decoder 解密
   *
   * @param data 要解密的字符串
   * @return 解密后的byte[]
   */
  public static byte[] decryptBASE64(String data) throws Exception {
    Decoder decoder = Base64.getDecoder();
    return decoder.decode(data);
  }


  //使用KeyPairGenerator 生成公私钥，存放于map对象中
  public static Map<String, Object> initKey() throws Exception {
    /* RSA算法要求有一个可信任的随机数源 */
    //获得对象 KeyPairGenerator 参数 RSA 1024个字节
    KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
    keyPairGen.initialize(1024);

    //通过对象 KeyPairGenerator 生成密匙对 KeyPair
    KeyPair keyPair = keyPairGen.generateKeyPair();

    //通过对象 KeyPair 获取RSA公私钥对象RSAPublicKey RSAPrivateKey
    RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
    RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
    //公私钥对象存入map中
    Map<String, Object> keyMap = new HashMap<>(2);
    keyMap.put(PUBLIC_KEY, publicKey);
    keyMap.put(PRIVATE_KEY, privateKey);
    return keyMap;
  }

  /**
   * 获取公私钥
   */
  public static synchronized RSA256Key getRSA256Key() throws Exception {
    if (rsa256Key == null) {
      synchronized (RSA256Key.class) {
        if (rsa256Key == null) {
          rsa256Key = new RSA256Key();
          Map<String, Object> map = initKey();
          rsa256Key.setPrivateKey((RSAPrivateKey) map.get(SecretKeyUtils.PRIVATE_KEY));
          rsa256Key.setPublicKey((RSAPublicKey) map.get(SecretKeyUtils.PUBLIC_KEY));
        }
      }
    }
    return rsa256Key;
  }

  public static void main(String[] args) {
    try {
      Map<String, Object> keyMap = initKey();  // 使用 java.security.KeyPairGenerator 生成 公/私钥
      String publicKey = getPublicKey(keyMap);
      System.out.println("公钥：\n" + publicKey);
      String privateKey = getPrivateKey(keyMap);
      System.out.println("私钥：\n" + privateKey);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
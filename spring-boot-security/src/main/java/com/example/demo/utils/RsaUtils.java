package com.example.demo.utils;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;
import org.apache.commons.codec.binary.Base64;

/**
 * @author : sean
 * @Project: jsencrypt
 * @date Date : 2021年09月23日 21:36
 * @Description: RSA加解密工具类，实现公钥加密私钥解密和私钥解密公钥解密
 */
public class RsaUtils {

  public static void main(String[] args) throws Exception {
    RSAKeyPair rsaKeyPair = generateKeyPair();
    test1(rsaKeyPair, "Hello World,世界你好！！！");
  }

  /**
   * 公钥加密私钥解密
   */
  private static void test1(RSAKeyPair keyPair, String source) throws Exception {
    System.out.println("***************** 公钥加密私钥解密开始 *****************");
    String publicKey = keyPair.getPublicKey();
    String privateKey = keyPair.getPrivateKey();
    System.out.println("公钥：" + publicKey);
    System.out.println("私钥：" + privateKey);
    String text1 = encryptByPublicKey(publicKey, source);
    String text2 = decryptByPrivateKey(privateKey, text1);
    System.out.println("加密前：" + source);
    System.out.println("加密后：" + text1);
    System.out.println("解密后：" + text2);
    if (source.equals(text2)) {
      System.out.println("解密字符串和原始字符串一致，解密成功");
    } else {
      System.out.println("解密字符串和原始字符串不一致，解密失败");
    }
    System.out.println("***************** 公钥加密私钥解密结束 *****************");
  }

  /**
   * 私钥加密公钥解密
   */
  private static void test2(RSAKeyPair keyPair, String source) throws Exception {
    System.out.println("***************** 私钥加密公钥解密开始 *****************");
    String text1 = encryptByPrivateKey(keyPair.getPrivateKey(), source);
    String text2 = decryptByPublicKey(keyPair.getPublicKey(), text1);
    System.out.println("加密前：" + source);
    System.out.println("加密后：" + text1);
    System.out.println("解密后：" + text2);
    if (source.equals(text2)) {
      System.out.println("解密字符串和原始字符串一致，解密成功");
    } else {
      System.out.println("解密字符串和原始字符串不一致，解密失败");
    }
    System.out.println("***************** 私钥加密公钥解密结束 *****************");
  }

  /**
   * 公钥解密
   */
  public static String decryptByPublicKey(String publicKeyText, String text) throws Exception {
    X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(Base64.decodeBase64(publicKeyText));
    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
    Cipher cipher = Cipher.getInstance("RSA");
    cipher.init(Cipher.DECRYPT_MODE, publicKey);
    byte[] result = cipher.doFinal(Base64.decodeBase64(text));
    return new String(result);
  }

  /**
   * 私钥加密
   */
  public static String encryptByPrivateKey(String privateKeyText, String text) throws Exception {
    PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKeyText));
    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
    Cipher cipher = Cipher.getInstance("RSA");
    cipher.init(Cipher.ENCRYPT_MODE, privateKey);
    byte[] result = cipher.doFinal(text.getBytes());
    return Base64.encodeBase64String(result);
  }

  /**
   * 私钥解密
   */
  public static String decryptByPrivateKey(String privateKeyText, String text) throws Exception {
    PKCS8EncodedKeySpec pkcs8EncodedKeySpec5 = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKeyText));
    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec5);
    Cipher cipher = Cipher.getInstance("RSA");
    cipher.init(Cipher.DECRYPT_MODE, privateKey);
    byte[] result = cipher.doFinal(Base64.decodeBase64(text));
    return new String(result);
  }

  /**
   * 公钥加密
   */
  public static String encryptByPublicKey(String publicKeyText, String text) throws Exception {
    X509EncodedKeySpec x509EncodedKeySpec2 = new X509EncodedKeySpec(Base64.decodeBase64(publicKeyText));
    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec2);
    Cipher cipher = Cipher.getInstance("RSA");
    cipher.init(Cipher.ENCRYPT_MODE, publicKey);
    byte[] result = cipher.doFinal(text.getBytes());
    return Base64.encodeBase64String(result);
  }

  /**
   * 构建RSA密钥对
   */
  public static RSAKeyPair generateKeyPair() throws NoSuchAlgorithmException {
    KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
    keyPairGenerator.initialize(1024);
    KeyPair keyPair = keyPairGenerator.generateKeyPair();
    RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
    RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
    String publicKeyString = Base64.encodeBase64String(rsaPublicKey.getEncoded());
    String privateKeyString = Base64.encodeBase64String(rsaPrivateKey.getEncoded());
    return new RSAKeyPair(publicKeyString, privateKeyString);
  }


}

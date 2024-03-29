package com.example.utils;

import com.example.base64.BASE64Decoder;
import com.example.base64.BASE64Encoder;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;

public class RSAUtils {

  //公钥加密
  public static String encrypt(String content, PublicKey publicKey) {
    try{
      Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");//java默认"RSA"="RSA/ECB/PKCS1Padding"
      cipher.init(Cipher.ENCRYPT_MODE, publicKey);
      byte[] output = cipher.doFinal(content.getBytes());
      BASE64Encoder encoder = new BASE64Encoder();
      return encoder.encode(output);
    }catch (Exception e){
      e.printStackTrace();
    }
    return null;
  }

  //公钥加密
  public static byte[] encrypt(byte[] content, PublicKey publicKey) {
    try{
      Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");//java默认"RSA"="RSA/ECB/PKCS1Padding"
      cipher.init(Cipher.ENCRYPT_MODE, publicKey);
      return  cipher.doFinal(content);
    }catch (Exception e){
      e.printStackTrace();
    }
    return null;
  }

  //私钥解密
  public static byte[] decrypt(byte[] content, PrivateKey privateKey) {
    try {
      Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
      cipher.init(Cipher.DECRYPT_MODE, privateKey);
      return cipher.doFinal(content);
    } catch (Exception e){
      e.printStackTrace();
      return null;
    }
  }
  //私钥解密
  public static String decrypt(String content, PrivateKey privateKey) {
    try {
      Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
      cipher.init(Cipher.DECRYPT_MODE, privateKey);
      byte [] b = cipher.doFinal(content.getBytes());
      BASE64Encoder encoder = new BASE64Encoder();
      return encoder.encode(b);
    } catch (Exception e){
      e.printStackTrace();
      return null;
    }
  }

  /**
   * String转公钥PublicKey
   * @param key
   * @return
   * @throws Exception
   */
  public static PublicKey getPublicKey(String key) throws Exception {
    byte[] keyBytes;
    keyBytes = (new BASE64Decoder()).decodeBuffer(key);
    X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    return keyFactory.generatePublic(keySpec);
  }

  /**
   * String转私钥PrivateKey
   * @param key
   * @return
   * @throws Exception
   */
  public static PrivateKey getPrivateKey(String key) throws Exception {
    byte[] keyBytes;
    keyBytes = (new BASE64Decoder()).decodeBuffer(key);
    PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    return keyFactory.generatePrivate(keySpec);
  }
}

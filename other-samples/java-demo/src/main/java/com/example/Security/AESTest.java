package com.example.Security;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.SecureRandom;
import java.util.Arrays;

public class AESTest {

  private String encodeResult;

  private String decodeResult;

  public AESTest() {

  }

  static void Test1() {
    System.out.println("请输入待编码字符串(以回车键结束)："); //此处存在一个乱码问题，在文本文档中的编码是GBK而它的编码是UTF-8，cmd不识别！
    String source = "";
    try {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      source = br.readLine();
    } catch (IOException e) {
      e.printStackTrace();
    }

    AESTest e = new AESTest();
    e.setEncodeResult(source);
    System.out.println("编码后结果：" + e.getEncodeResult());
    e.setDecodeResult(e.getEncodeResult());
    System.out.println("解码后结果：" + e.getDecodeResult());
  }

  static void Test2() throws Exception {
    String content = "我爱你,祖国";
    System.out.println("加密前：" + content);
    String key = "123456";
    System.out.println("加密密钥和解密密钥：" + key);
    String encrypt = aesEncrypt(content, key);
    System.out.println("加密后：" + encrypt);
    String decrypt = aesDecrypt(encrypt, key);
    System.out.println("解密后：" + decrypt);
  }

  public static void main(String[] args) throws Exception {
    Test2();
  }

  /**
   * AES加密为base 64 code
   *
   * @param content    待加密的内容
   * @param encryptKey 加密密钥
   * @return 加密后的base 64 code
   * @throws Exception
   */

  public static String aesEncrypt(String content, String encryptKey) throws Exception {
    return base64Encode(aesEncryptToBytes(content, encryptKey));
  }

  /**
   * AES加密
   *
   * @param content    待加密的内容
   * @param encryptKey 加密密钥
   * @return 加密后的byte[]
   * @throws Exception
   */

  public static byte[] aesEncryptToBytes(String content, String encryptKey) throws Exception {
    KeyGenerator kgen = KeyGenerator.getInstance("AES");
    kgen.init(128, new SecureRandom(encryptKey.getBytes()));
    Cipher cipher = Cipher.getInstance("AES");
    cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(kgen.generateKey().getEncoded(), "AES"));
    return cipher.doFinal(content.getBytes("utf-8"));
  }

  /**
   * base 64 encode
   *
   * @param bytes 待编码的byte[]
   * @return 编码后的base 64 code
   */

  public static String base64Encode(byte[] bytes) {
    return new BASE64Encoder().encode(bytes);
  }

  /**
   * 将base 64 code AES解密
   *
   * @param encryptStr 待解密的base 64 code
   * @param decryptKey 解密密钥
   * @return 解密后的string
   * @throws Exception
   */

  public static String aesDecrypt(String encryptStr, String decryptKey) throws Exception {
    return aesDecryptByBytes(base64Decode(encryptStr), decryptKey);
  }

  /**
   * AES解密
   *
   * @param encryptBytes 待解密的byte[]
   * @param decryptKey   解密密钥
   * @return 解密后的String
   * @throws Exception
   */

  public static String aesDecryptByBytes(byte[] encryptBytes, String decryptKey) throws Exception {
    KeyGenerator kgen = KeyGenerator.getInstance("AES");
    kgen.init(128, new SecureRandom(decryptKey.getBytes()));
    Cipher cipher = Cipher.getInstance("AES");
    cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(kgen.generateKey().getEncoded(), "AES"));
    byte[] decryptBytes = cipher.doFinal(encryptBytes);
    return new String(decryptBytes);
  }

  /**
   * base 64 decode
   *
   * @param base64Code 待解码的base 64 code
   * @return 解码后的byte[]
   * @throws Exception
   */

  public static byte[] base64Decode(String base64Code) throws Exception {
    return new BASE64Decoder().decodeBuffer(base64Code);
  }

  public String getEncodeResult() {
    return encodeResult;
  }

  //编码设置
  public void setEncodeResult(String encodeResult) {
    char[] src = encodeResult.toCharArray();
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < src.length; i++) {
      if (Character.isDigit(src[i])) {
        if (i != src.length - 1) {
          char[] temp = new char[Character.getNumericValue(src[i]) + 1];
          Arrays.fill(temp, src[i + 1]);
          sb.append(temp);
          sb.append("_");
        } else {
          sb.append(src[i]);
        }
      } else if (src[i] == '_') {
        sb.append("\\UL");
        sb.append("_");
      } else if (i == src.length - 1) {
        sb.append(src[i]);
      } else {
        sb.append(src[i]);
        sb.append("_");
      }

    }

    this.encodeResult = new String(sb);//创建返回编码后字串

  }

  public String getDecodeResult() {
    return decodeResult;
  }

  public void setDecodeResult(String encodeResult) {
    String[] temp = encodeResult.split("_");
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < temp.length; i++) {
      if (temp[i].equals("\\UL")) {
        sb.append("_");
      } else if (temp[i].length() > 1) {
        sb.append(temp[i].length() - 1);
      } else {
        sb.append(temp[i]);
      }
    }
    this.decodeResult = new String(sb);
  }

}

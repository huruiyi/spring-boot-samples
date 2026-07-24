package com.example.demo.utils;

/**
 * RSA密钥对对象
 */
public class RSAKeyPair {

  private final String publicKey;
  private final String privateKey;

  public RSAKeyPair(String publicKey, String privateKey) {
    this.publicKey = publicKey;
    this.privateKey = privateKey;
  }

  public String getPublicKey() {
    return publicKey;
  }

  public String getPrivateKey() {
    return privateKey;
  }

}

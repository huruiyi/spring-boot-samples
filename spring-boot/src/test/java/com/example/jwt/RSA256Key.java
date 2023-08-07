package com.example.jwt;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import lombok.Data;

@Data
public class RSA256Key {

  private RSAPublicKey publicKey;
  private RSAPrivateKey privateKey;
}

package com.example.authorizationserver;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import java.io.FileInputStream;
import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;


public class AuthorizationserverApplicationTests {

    public static byte[] decryptBASE64(String key) throws Exception {
        return (new BASE64Decoder()).decodeBuffer(key);
    }


    public static String encryptBASE64(byte[] key) throws Exception {
        return (new BASE64Encoder()).encodeBuffer(key).replace("\r", "").replace("\n", "");
    }

    public static final String KEY_STORE = "JKS";

    public static final String X509 = "X.509";

    private static KeyStore getKeyStore(String keyStorePath, String password)
            throws Exception {

        FileInputStream is = new FileInputStream(keyStorePath);
        KeyStore ks = KeyStore.getInstance(KEY_STORE);
        ks.load(is, password.toCharArray());
        is.close();
        return ks;
    }


    private static PrivateKey getPrivateKey(String keyStorePath, String alias, String storePass, String keyPass) throws Exception {
        KeyStore ks = getKeyStore(keyStorePath, storePass);
        PrivateKey key = (PrivateKey) ks.getKey(alias, keyPass.toCharArray());
        return key;
    }


    private static PublicKey getPublicKey(String keyStorePath, String alias, String storePass) throws Exception {
        KeyStore ks = getKeyStore(keyStorePath, storePass);
        PublicKey key = ks.getCertificate(alias).getPublicKey();
        return key;
    }


    public static String getStrPublicKey(String keyStorePath, String alias, String storePass) throws Exception {
        PublicKey key = getPublicKey(keyStorePath, alias, storePass);
        String strKey = encryptBASE64(key.getEncoded());
        return strKey;
    }


    public static String getStrPrivateKey(String keyStorePath, String alias, String storePass, String keyPass) throws Exception {

        PrivateKey key = getPrivateKey(keyStorePath, alias, storePass, keyPass);
        String strKey = encryptBASE64(key.getEncoded());
        return strKey;
    }


    public static String encryptByPublicKey(String publicKey, String srcData) throws Exception {

        byte[] pk = decryptBASE64(publicKey);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(pk);
        KeyFactory kf = KeyFactory.getInstance("RSA");

        PublicKey pubKey = kf.generatePublic(spec);


        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);

        byte[] doFinal = cipher.doFinal(srcData.getBytes());
        return encryptBASE64(doFinal);
    }

    public static String descryptByPrivateKey(String privateKey, String data) throws Exception {
        byte[] pk = decryptBASE64(privateKey);
        byte[] text = decryptBASE64(data);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(pk);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PrivateKey prvKey = kf.generatePrivate(spec);

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, prvKey);

        byte[] doFinal = cipher.doFinal(text);
        return new String(doFinal);
    }

    public static void main(String[] args) {

        String strPublicKey = "";

        String strPrivateKey = "";

        try {
            strPublicKey = getStrPublicKey("d:/logs/key/test.jks ", "root", "123456789");
            System.out.println("公钥 = 【" + strPublicKey + "】");

            strPrivateKey = getStrPrivateKey("d:/logs/key/test.jks ", "root", "123456789", "123456789");
            System.out.println("\n私钥 = 【" + strPrivateKey + "】");

        } catch (Exception e1) {
            e1.printStackTrace();
        }

        String originalText = "Java实现RSA加密算法！";

        try {
            String secretText = encryptByPublicKey(strPublicKey, originalText);
            System.out.println("\n经RSA公钥加密后 = " + secretText);
            System.out.println("\n经RSA公钥加密后长度 = " + secretText.length());

            String text = descryptByPrivateKey(strPrivateKey, secretText);
            System.out.println("\n经RSA私钥解密后 = 【" + text + "】");
            System.out.println("\n经RSA私钥解密后长度 = 【" + text.length() + "】");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }



}

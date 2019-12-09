package com.example.Security;

import javax.activation.DataContentHandler;
import javax.xml.bind.DatatypeConverter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.Set;

public class Algorithms {
    public static void main(String[] args) throws Exception {
        String originalContent = "123456";
        Set<String> availableAlgorithms = Security.getAlgorithms("MessageDigest");
        for (String each : availableAlgorithms) {
            digest(each, originalContent.getBytes());
        }

        String fileMd5Info = getFileMd5Info("D:\\消息中间件(二期).xmind");
        System.out.println(fileMd5Info);
    }

    private static void digest(String algorithm, byte[] content) throws Exception {
        MessageDigest instance = MessageDigest.getInstance(algorithm);
        instance.update(content);
        //当所有数据已被更新,调用digest()方法完成哈希计算,返回字节数组
        byte[] digest = instance.digest();
        System.out.println("算法=" + algorithm + ",摘要=" + DatatypeConverter.printHexBinary(digest));
    }

    private static String getFileMd5Info(String filePath) throws IOException, NoSuchAlgorithmException {
        FileInputStream inputStream = new FileInputStream(filePath);
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        //DigestInputStream digestInputStream = new DigestInputStream(inputStream, messageDigest);
        String message = DatatypeConverter.printHexBinary(messageDigest.digest());
        inputStream.close();
        return message;
    }
    //https://blog.csdn.net/aitangyong/article/details/53858338
}

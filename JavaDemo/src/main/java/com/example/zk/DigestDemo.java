package com.example.zk;

import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;

import java.security.NoSuchAlgorithmException;


public class DigestDemo {

    public static void Demo01() throws NoSuchAlgorithmException {
        String digest = DigestAuthenticationProvider.generateDigest("admin:123456");
        System.out.println(digest);

    }

    /*
     * 1:创建回话
     * 2:创建节点
     * 3:读取数据
     * 4:更新数据
     * 5:检测节点是否存在
     * 6:权限控制
     * 7:watch
     * */
    public static void main(String[] args) throws NoSuchAlgorithmException {


    }
}

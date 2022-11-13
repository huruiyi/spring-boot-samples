package com.example.demo;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordTests {
    public static void main(String[] args) {
        String pass = "123";
        BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
        String hashPass = bcryptPasswordEncoder.encode(pass);
        System.out.println("***************************");
        System.out.println(hashPass);
        System.out.println("***************************");
        boolean f = bcryptPasswordEncoder.matches("123", hashPass);
        System.out.println(f);
    }
}

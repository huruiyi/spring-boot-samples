package com.example;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordTests {
    public static void main(String[] args) {
        String pass = "admin";
        BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
        String hashPass = bcryptPasswordEncoder.encode(pass);
        System.out.println("***************************");
        System.out.println(hashPass);
        System.out.println("***************************");
        boolean f = bcryptPasswordEncoder.matches("admin", hashPass);
        System.out.println(f);
    }
}

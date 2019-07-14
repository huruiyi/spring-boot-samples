package com.example.Spring.aspect.Demo1.service.impl;

import com.example.Spring.aspect.Demo1.service.Person;
import org.springframework.stereotype.Component;

@Component("women")
public class Women implements Person {

    @Override
    public void likePerson() {
        System.out.println("我是女生，我喜欢帅哥");
    }

    @Override
    public void sayHello() {
        System.out.println("帅哥!!你好");
    }
}

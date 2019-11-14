package com.apress.prospring4.ch2._Aspect.service.impl;

import com.apress.prospring4.ch2._Aspect.service.Person;
import org.springframework.stereotype.Component;

@Component("women")
public class Women implements Person {

    public void likePerson() {
        System.out.println("我是女生，我喜欢帅哥");
    }

    public void sayHello() {
        System.out.println("帅哥!!你好");
    }
}

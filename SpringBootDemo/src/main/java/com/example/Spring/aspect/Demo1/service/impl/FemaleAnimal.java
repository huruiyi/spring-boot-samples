package com.example.Spring.aspect.Demo1.service.impl;

import com.example.Spring.aspect.Demo1.service.Animal;
import org.springframework.stereotype.Component;

@Component
public class FemaleAnimal implements Animal {

    @Override
    public void eat() {
        System.out.println("我是雌性，我比雄性更喜欢吃零食");
    }
}

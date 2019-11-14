package com.apress.prospring4.ch2._Aspect.service.impl;

import com.apress.prospring4.ch2._Aspect.service.Animal;
import org.springframework.stereotype.Component;

@Component
public class FemaleAnimal implements Animal {

     public void eat() {
        System.out.println("我是雌性，我比雄性更喜欢吃零食");
    }
}

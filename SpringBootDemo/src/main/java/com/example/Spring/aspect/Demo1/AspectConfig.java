package com.example.Spring.aspect.Demo1;

import com.example.Spring.aspect.Demo1.service.Animal;
import com.example.Spring.aspect.Demo1.service.impl.FemaleAnimal;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectConfig {
    //"+"表示person的所有子类；defaultImpl 表示默认需要添加的新的类
    @DeclareParents(value = "com.example.Spring.aspect.Demo1.service.Person+", defaultImpl = FemaleAnimal.class)
    public Animal animal;
}

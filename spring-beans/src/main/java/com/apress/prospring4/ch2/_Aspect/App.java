package com.apress.prospring4.ch2._Aspect;

import com.apress.prospring4.ch2._Aspect.service.Animal;
import com.apress.prospring4.ch2._Aspect.service.Person;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 来源:
 * https://blog.csdn.net/u010502101/article/details/76944753
 */
public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AnnotationConfig.class);

        Person person = (Person) ctx.getBean("women");
        person.likePerson();
        person.sayHello();

        Animal animal = (Animal) person;
        animal.eat();
    }
}

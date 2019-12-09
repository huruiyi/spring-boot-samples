package com.example.GenericType.OOP8;

public class App {
    public static void main(String[] args) {
        ActionAfterClick a = new ActionAfterClick() {
            @Override
            public void action() {
                System.out.println("I'm action from anonymous class");
            }
        };

        Button p = new Button();
        Button p2 = new Button();

        p2.addAction(new ActionAfterClick() {
            @Override
            public void action() {
                System.out.println("I'm action from anonymous class for button p2");
            }
        });
        p.addAction(a);
    }
}

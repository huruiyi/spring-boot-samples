package com.example.GenericType.OOP7;

public class App {
    public static void main(String[] args) {
        ActionAfterClick x = () -> {
            System.out.println("I'm action from anonymous class");
        };

        ActionAfterClick2 x2 = (a, b) -> a + b;

        Button p = new Button();
        Button p2 = new Button();

        p2.addAction(new ActionAfterClick() {

            @Override
            public void action() {
                System.out.println("I'm action from anonymous class for button p2");
            }
        });
        p.addAction(x);

        int result = p.addAction2(10, 15, x2);

        System.out.println(result);
    }
}

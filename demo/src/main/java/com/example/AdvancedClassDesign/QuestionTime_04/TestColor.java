package com.example.AdvancedClassDesign.QuestionTime_04;

class Shape {
    private boolean isDisplayed;
    protected int canvasID;

    public Shape() {
        isDisplayed = false;
        canvasID = 0;
    }

    public class Color {
        public void display() {
            System.out.println("isDisplayed: " + isDisplayed);
            System.out.println("canvasID: " + canvasID);
        }
    }
}

class TestColor {
    public static void main(String[] args) {
        Shape.Color black = new Shape().new Color();
        black.display();
    }
}

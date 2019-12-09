package com.example.GenericType;

public class Box<T> {
    T element;

    void setElement(T valueOfElement) {
        this.element = valueOfElement;
    }

    T getElement() {
        return this.element;
    }
}
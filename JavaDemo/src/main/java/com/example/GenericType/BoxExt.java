package com.example.GenericType;

public  class BoxExt<T extends Employee> {
    T element;

    void setElement(T valueOfElement) {
        this.element = valueOfElement;
    }

    T getElement() {
        return this.element;
    }
}
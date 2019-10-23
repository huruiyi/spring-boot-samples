package com.example.GenericType.OOP7;

class Button {
    void addAction(ActionAfterClick a) {
        a.action();
    }

    int addAction2(int x, int y, ActionAfterClick2 a) {
        return a.action2(x, y);
    }
}

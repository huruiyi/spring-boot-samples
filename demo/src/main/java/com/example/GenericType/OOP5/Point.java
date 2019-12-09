package com.example.GenericType.OOP5;


class Point {
    private int x;
    private int y;

    Point() {
    }

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;
        if (this.getClass() != o.getClass())
            return false;

        Point sentPoint = (Point) o;

        return this.x == sentPoint.x && this.y == sentPoint.y;
    }

    @Override
    public String toString() {
        return getX() + "----" + getY();
    }

}
package com.pong.physics;

public class Vector {
    public double x, y;

    public Vector() {
        x = y = 0;
    }
    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setXY(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getMagnitude() {
        return Math.sqrt(x*x + y*y);
    }
    public double getDirection() {
        return Math.atan(y / x);
    }

    public Vector add(Vector v) {
        return new Vector(this.x + v.x, this.y + v.y);
    }
    public Vector subtract(Vector v) {
        return new Vector(this.x - v.x, this.y - v.y);
    }
    public Vector multiply(double c) {
        return new Vector(this.x * c, this.y * c);
    }
    public Vector divide(double c) {
        return new Vector(this.x / c, this.y / c);
    }
    public double dot(Vector v) {
        return this.x*v.x + this.y*v.y;
    }

    public String toString() {
        return "Vector(" + x + ", " + y + ")";
    }
}

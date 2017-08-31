package com.pong.physics;

public class VectorBuilder {
    private double x, y;

    public VectorBuilder(Vector vector) {
        x = vector.x;
        y = vector.y;
    }

    public VectorBuilder(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public VectorBuilder normalized() {
        double mag = Math.sqrt(x*x + y*y);
        if (mag != 0) {
            x /= mag;
            y /= mag;
        }
        return this;
    }

    public VectorBuilder perpendicular() {
        double temp = y;

        y = x;
        x = -temp;

        return this;
    }
    public VectorBuilder reversed() {
        x = -x;
        y = -y;

        return this;
    }

    public Vector buildVector() {
        return new Vector(x, y);
    }
}

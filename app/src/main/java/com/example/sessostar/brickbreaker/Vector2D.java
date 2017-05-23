package com.example.sessostar.brickbreaker;

/**
 * Created by user on 23/05/17.
 */

public class Vector2D {
    public float x, y;

    public Vector2D(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float dot(Vector2D v) {
        return x * v.x + y * v.y;
    }

    public float croos(Vector2D v) {
        return x * v.y - y * v.x;
    }

    public Vector2D minus(Vector2D v) {
        return new Vector2D(x - v.x, y - v.y);
    }

    public Vector2D plus(Vector2D v) {
        return new Vector2D(x + v.x, y + v.y);
    }

    public Vector2D times(float a) {
        return new Vector2D(x * a, y * a);
    }

    public float norm() {
        return this.dot(this);
    }

    public float norm2() {
        float norm = this.dot(this);
        return norm * norm;
    }
}

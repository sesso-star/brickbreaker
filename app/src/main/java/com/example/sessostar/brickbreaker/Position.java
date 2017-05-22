package com.example.sessostar.brickbreaker;

/**
 * Created by user on 21/05/17.
 */

public class Position {
    public float x, y;

    public Position(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void updatePosition(Velocity v, float dt) {
        x += v.x * dt;
        y += v.y * dt;
    }
}

package com.example.sessostar.brickbreaker;

/**
 * Created by user on 21/05/17.
 */

public class Position extends Vector2D {
    public Position(float x, float y) {
        super(x, y);
    }

    public void updatePosition(Velocity v, float dt) {
        x += v.x * dt;
        y += v.y * dt;
    }
}

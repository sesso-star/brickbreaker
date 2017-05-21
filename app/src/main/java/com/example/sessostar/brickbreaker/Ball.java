package com.example.sessostar.brickbreaker;

import android.opengl.Matrix;
import android.os.SystemClock;

/**
 * Created by user on 21/05/17.
 */

public class Ball {
    private Circle circle;
    private Position pos;


    private final float[] translationMatrix = new float[16];

    public Ball(float radius) {
        circle = new Circle(radius, 40);
        pos = new Position(0, 0);
    }

    public Position getPos() {
        return pos;
    }

    public void setPos(float x, float y) {
        pos.x = x;
        pos.y = y;
    }

    public void draw(float[] mvpMatrix) {
        float[] scratch = new float[16];


        Matrix.setIdentityM(scratch, 0);
        Matrix.translateM(translationMatrix, 0, scratch, 0, pos.x, pos.y, 0);

        Matrix.multiplyMM(scratch, 0, mvpMatrix, 0, translationMatrix, 0);

        circle.draw(scratch);
    }
}

package com.example.sessostar.brickbreaker;

import android.opengl.Matrix;
import android.os.SystemClock;

/**
 * Created by user on 21/05/17.
 */

public class Ball {
    private Circle circle;
    private Position pos;

    ShaderHandler shaderHandler;

    public Ball(float radius, ShaderHandler sh) {
        shaderHandler = sh;

        circle = new Circle(radius, 40, sh);
        pos = new Position(0, 0);
    }

    public Position getPos() {
        return pos;
    }

    public void setPos(float x, float y) {
        pos.x = x;
        pos.y = y;
    }

    public void draw() {
        float[] translationMatrix = new float[16];

        Matrix.setIdentityM(translationMatrix, 0);
        Matrix.translateM(translationMatrix, 0, pos.x, pos.y, 0);

        shaderHandler.setModelMatrix(translationMatrix);

        circle.draw();
    }
}

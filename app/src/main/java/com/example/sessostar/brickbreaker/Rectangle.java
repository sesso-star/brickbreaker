package com.example.sessostar.brickbreaker;

import android.opengl.Matrix;

/**
 * Created by gustavo on 5/22/17.
 */

public class Rectangle {

    private Quadrilateral quadrilateral;
    private ShaderHandler shaderHandler;

    public Rectangle(float width, float height, ShaderHandler sh) {
        shaderHandler = sh;

        Position[] rectVertice = new Position[4];
        rectVertice[0] = new Position((-1) * (width / 2), (-1) * (height / 2));
        rectVertice[1] = new Position(( 1) * (width / 2), (-1) * (height / 2));
        rectVertice[2] = new Position(( 1) * (width / 2), ( 1) * (height / 2));
        rectVertice[3] = new Position((-1) * (width / 2), ( 1) * (height / 2));;
        quadrilateral = new Quadrilateral(rectVertice, sh);
    }


    public void draw() {
        float[] translationMatrix = new float[16];
        Matrix.setIdentityM(translationMatrix, 0);
        Matrix.translateM(translationMatrix, 0, 0, 0, 0);
        shaderHandler.setModelMatrix(translationMatrix);

        quadrilateral.draw();
    }
}

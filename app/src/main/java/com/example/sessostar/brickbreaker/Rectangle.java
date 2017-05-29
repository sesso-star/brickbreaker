package com.example.sessostar.brickbreaker;

import android.opengl.Matrix;

/**
 * Created by gustavo on 5/22/17.
 */

public class Rectangle {

    protected Quadrilateral quadrilateral;
    protected Position pos;
    protected ShaderHandler shaderHandler;
    protected Velocity velocity;
    protected float width, height;


    /**
     * @param width is the width of the rectangle
     * @param height is the height of the rectangle
     * @param sh is the ShaderHandler which will draw this object */
    public Rectangle(float width, float height, ShaderHandler sh) {
        shaderHandler = sh;
        this.width = width;
        this.height = height;

        Position[] rectVertice = new Position[4];
        rectVertice[0] = new Position((-1) * (width / 2), (-1) * (height / 2));
        rectVertice[1] = new Position(( 1) * (width / 2), (-1) * (height / 2));
        rectVertice[2] = new Position(( 1) * (width / 2), ( 1) * (height / 2));
        rectVertice[3] = new Position((-1) * (width / 2), ( 1) * (height / 2));;
        quadrilateral = new Quadrilateral(rectVertice, sh);

        pos = new Position(0, 0);
        velocity = new Velocity(0, 0);
    }

    public Position getPos() {
        return pos;
    }

    public void setPos(float x, float y) {
        pos.x = x;
        pos.y = y;
    }


    public Velocity getVelocity() {
        return velocity;
    }


    public void setVelocity(float vx, float vy) {
        velocity.x = vx;
        velocity.y = vy;
    }

    float getWidth() {
        return width;
    }

    float getHeight() {
        return height;
    }

    void setColor(float[] color) {
        this.quadrilateral.setColor(color);
    }

    void draw() {
        pos.updatePosition(velocity, Utils.dt);
        float[] translationMatrix = new float[16];
        Matrix.setIdentityM(translationMatrix, 0);
        Matrix.translateM(translationMatrix, 0, pos.x, pos.y, 0);
        shaderHandler.setModelMatrix(translationMatrix);

        quadrilateral.draw();
    }
}

package com.example.sessostar.brickbreaker;

import android.opengl.GLES20;
import android.opengl.Matrix;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * Implements a Rectangular object
 */

public class Quadrilateral {
    private FloatBuffer vertexBuffer;

    private int COORDS_PER_VERTEX = 3;
    float color[] = {1.f, 0.71372549f, 0.31372549f, 1.0f};

    ShaderHandler shaderHandler;

    /**
     * @param vertices is an array of Position and should be in counter-clockwise order
     * @param sh is the shader handler to this object
     */
    public Quadrilateral(Position[] vertices, ShaderHandler sh) {
        shaderHandler = sh;

        int idx = 0;
        float[] rectCoords = new float[3 * (4 + 1)];
        rectCoords[idx++] = vertices[1].x;
        rectCoords[idx++] = vertices[1].y;
        rectCoords[idx++] = 0.0f;
        rectCoords[idx++] = vertices[0].x;
        rectCoords[idx++] = vertices[0].y;
        rectCoords[idx++] = 0.0f;
        rectCoords[idx++] = vertices[2].x;
        rectCoords[idx++] = vertices[2].y;
        rectCoords[idx++] = 0.0f;
        rectCoords[idx++] = vertices[3].x;
        rectCoords[idx++] = vertices[3].y;
        rectCoords[idx++] = 0.0f;

        // 4 bytes per float
        ByteBuffer bb = ByteBuffer.allocateDirect(rectCoords.length * 4);
        bb.order(ByteOrder.nativeOrder());

        vertexBuffer = bb.asFloatBuffer();
        vertexBuffer.put(rectCoords);
        vertexBuffer.position(0);
    }


    public void setColor(float[] color) {
        this.color = color;
    }


    public void draw() {
        int vertexStride = COORDS_PER_VERTEX * 4; // 4 bytes per vertex
        shaderHandler.setPosition(COORDS_PER_VERTEX, vertexStride, vertexBuffer);
        shaderHandler.setColor(color);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, 4);
    }
}
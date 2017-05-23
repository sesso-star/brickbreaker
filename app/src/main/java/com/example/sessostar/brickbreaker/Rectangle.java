package com.example.sessostar.brickbreaker;

import android.opengl.GLES20;
import android.opengl.Matrix;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * Implements a Rectangular object
 */

public class Rectangle {
    private FloatBuffer vertexBuffer;

    static float centerX = 0;
    static float centerY = 0;

    private int COORDS_PER_VERTEX = 3;
    float color[] = {1.f, 0.31372549f, 0.31372549f, 1.0f};

    ShaderHandler shaderHandler;

    /**
     * @param width Rectangle width
     * @param height Rectangle height
     */
    public Rectangle(float width, float height, ShaderHandler sh) {
        shaderHandler = sh;

        int idx = 0;
        float[] rectCoords = new float[3 * (4 + 1)];
        rectCoords[idx++] = centerX + (-1) * (width / 2);
        rectCoords[idx++] = centerY + (-1) * (height / 2);
        rectCoords[idx++] = 0.0f;
        rectCoords[idx++] = centerX + (-1) * (width / 2);
        rectCoords[idx++] = centerY + ( 1) * (height / 2);
        rectCoords[idx++] = 0.0f;
        rectCoords[idx++] = centerX + ( 1) * (width / 2);
        rectCoords[idx++] = centerY + (-1) * (height / 2);
        rectCoords[idx++] = 0.0f;
        rectCoords[idx++] = centerX + ( 1) * (width / 2);
        rectCoords[idx++] = centerY + ( 1) * (height / 2);
        rectCoords[idx++] = 0.0f;

        // 4 bytes per float
        ByteBuffer bb = ByteBuffer.allocateDirect(rectCoords.length * 4);
        bb.order(ByteOrder.nativeOrder());

        vertexBuffer = bb.asFloatBuffer();
        vertexBuffer.put(rectCoords);
        vertexBuffer.position(0);
    }


    public void draw() {
        float[] translationMatrix = new float[16];

        Matrix.setIdentityM(translationMatrix, 0);
        Matrix.translateM(translationMatrix, 0, 0, 0, 0);
        shaderHandler.setModelMatrix(translationMatrix);


        int vertexStride = COORDS_PER_VERTEX * 4; // 4 bytes per vertex
        shaderHandler.setPosition(COORDS_PER_VERTEX, vertexStride, vertexBuffer);
        shaderHandler.setColor(color);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, 4);
    }
}
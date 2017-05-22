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

    private float width;
    private float height;

    private int COORDS_PER_VERTEX = 3;

    float color[] = {1.f, 0.31372549f, 0.31372549f, 1.0f};

    ShaderHandler shaderHandler;

    /**
     * @param width Rectangle width
     * @param height Rectangle height
     */
    public Rectangle(float width, int height, ShaderHandler sh) {
        this.width = width;
        this.height = height;
        shaderHandler = sh;

        float[] rectCoords = new float[3 * vertexCount + 1];

        int idx = 0;
        circleCoords[idx++] = centerX;
        circleCoords[idx++] = centerY;
        circleCoords[idx++] = 0.0f;

        for (int i = 0; i <= vertexCount - 2; i++) {
            float rad = ((float) i / (vertexCount - 2)) * 2 * (float)Math.PI;

            circleCoords[idx++] = centerX + radius * (float)Math.cos(rad);
            circleCoords[idx++] = centerY + radius * (float)Math.sin(rad);
            circleCoords[idx++] = 0.0f;
        }

        // 4 bytes per float
        ByteBuffer bb = ByteBuffer.allocateDirect(circleCoords.length * 4);
        bb.order(ByteOrder.nativeOrder());

        vertexBuffer = bb.asFloatBuffer();
        vertexBuffer.put(circleCoords);
        vertexBuffer.position(0);

    }


    public void draw() {
        int vertexStride = COORDS_PER_VERTEX * 4; // 4 bytes per vertex

        shaderHandler.setPosition(COORDS_PER_VERTEX, vertexStride, vertexBuffer);
        shaderHandler.setColor(color);
//        shaderHandler.disablePositionHandle();

        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_FAN, 0, vertexCount);
    }
}
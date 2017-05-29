package com.example.sessostar.brickbreaker;

import android.opengl.GLES20;
import android.opengl.Matrix;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * Created by user on 20/05/17.
 */

public class Circle {
    private FloatBuffer vertexBuffer;

    static float centerX = 0;
    static float centerY = 0;

    private int vertexCount;
    private int COORDS_PER_VERTEX = 3;

    float color[] = {0.76953125f, 0.22265625f, 0.63671875f, 1.0f};

    ShaderHandler shaderHandler;

    /**
     *
     * @param radius Circle radius
     * @param precision number of points used to draw de circumpherence
     */
    public Circle(float radius, int precision, ShaderHandler sh) {
        vertexCount = precision;
        shaderHandler = sh;

        float[] circleCoords = new float[3 * vertexCount + 1];

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

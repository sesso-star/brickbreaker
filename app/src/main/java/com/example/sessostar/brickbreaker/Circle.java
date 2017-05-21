package com.example.sessostar.brickbreaker;

import android.opengl.GLES20;

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

    private final int mProgram;
    private int mPositionHandle;
    private int mColorHandle;

    private int mMVPMatrixHandle;


    float color[] = {0.63671875f, 0.76953125f, 0.22265625f, 1.0f};

    private final String vertexShaderCode =
            "uniform mat4 uMVPMatrix;" +
            "attribute vec4 vPosition;" +
            "void main() {" +
            "  gl_Position = uMVPMatrix * vPosition;" +
            "}";

    private final String fragmentShaderCode =
            "precision mediump float;" +
            "uniform vec4 vColor;" +
            "void main() {" +
            "  gl_FragColor = vColor;" +
            "}";

    /**
     *
     * @param radius Circle radius
     * @param precision number of points used to draw de circumpherence
     */
    public Circle(float radius, int precision) {
        vertexCount = precision;
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

        int vertexShader = GameRenderer.loadShader(GLES20.GL_VERTEX_SHADER, vertexShaderCode);
        int fragmentShader = GameRenderer.loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode);

        mProgram = GLES20.glCreateProgram();
        GLES20.glAttachShader(mProgram, vertexShader);
        GLES20.glAttachShader(mProgram, fragmentShader);
        GLES20.glLinkProgram(mProgram);
    }


    public void draw(float[] mvpMatrix) {
        int vertexStride = COORDS_PER_VERTEX * 4; // 4 bytes per vertex

        GLES20.glUseProgram(mProgram);
        mPositionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");
        GLES20.glEnableVertexAttribArray(mPositionHandle);
        GLES20.glVertexAttribPointer(mPositionHandle, COORDS_PER_VERTEX,
                                     GLES20.GL_FLOAT, false,
                                     vertexStride, vertexBuffer);

        mColorHandle = GLES20.glGetUniformLocation(mProgram, "vColor");
        GLES20.glUniform4fv(mColorHandle, 1, color, 0);

        mMVPMatrixHandle = GLES20.glGetUniformLocation(mProgram, "uMVPMatrix");
        GLES20.glUniformMatrix4fv(mMVPMatrixHandle, 1, false, mvpMatrix, 0);

        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_FAN, 0, vertexCount);
        GLES20.glDisableVertexAttribArray(mPositionHandle);
    }


}

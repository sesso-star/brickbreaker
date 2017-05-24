package com.example.sessostar.brickbreaker;

import android.graphics.Shader;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.Matrix;
import android.os.SystemClock;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import static java.lang.Math.sin;

/**
 * Created by user on 20/05/17.
 */

public class GameRenderer implements Renderer{
    Ball ball;
    Rectangle rectangle1, rectangle2, rectangle3, rectangle4;

    ShaderHandler shaderHandler;

    private final float[] mProjectionMatrix = new float[16];
    private final float[] mViewMatrix = new float[16];

    public void onSurfaceCreated(GL10 unused, EGLConfig config) {
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

        shaderHandler = new ShaderHandler();
        Matrix.setLookAtM(mViewMatrix, 0,
                0f, 0f, -3f,
                0f, 0f, 0f,
                0f, 1f, 0f);
        shaderHandler.setViewMatrix(mViewMatrix);


        ball = new Ball(0.5f, shaderHandler);
        ball.setPos(4, 1);
        ball.setVelocity(-50, 50);

        rectangle1 = new Rectangle(0.2f, 2f, shaderHandler);
        rectangle1.setPos(0f, 5f);

        rectangle2 = new Rectangle(2f, 0.2f, shaderHandler);
        rectangle2.setPos(5f, 10f);

        rectangle3 = new Rectangle(0.2f, 2f, shaderHandler);
        rectangle3.setPos(10f, 5f);

        rectangle4 = new Rectangle(2f, 0.2f, shaderHandler);
        rectangle4.setPos(5f, 0f);
    }

    public void onDrawFrame(GL10 unused) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

        ball.checkColisionWith(rectangle1);
        ball.checkColisionWith(rectangle2);
        ball.checkColisionWith(rectangle3);
        ball.checkColisionWith(rectangle4);

        if (time % 10 == 1)
            SoundFXPlayer.playBallCollisionSound();


        ball.draw();
        rectangle1.draw();
        rectangle2.draw();
        rectangle3.draw();
        rectangle4.draw();
    }

    public void onSurfaceChanged(GL10 unused, int width, int height) {
        GLES20.glViewport(0, 0, width, height);

        float ratio = (float) width / height;
        Utils.ySize = Utils.xSize / ratio;

        Matrix.frustumM(mProjectionMatrix, 0, 0, Utils.xSize, 0, Utils.ySize, 2.9f, 10);
        shaderHandler.setProjectionMatrix(mProjectionMatrix);
    }



}


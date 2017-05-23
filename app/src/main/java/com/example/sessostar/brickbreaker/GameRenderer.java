package com.example.sessostar.brickbreaker;

import android.content.Context;
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

    private Context context;

    Ball ball;
    Ball ball2;
    Rectangle rectangle1;

    ShaderHandler shaderHandler;

    private final float[] mProjectionMatrix = new float[16];
    private final float[] mViewMatrix = new float[16];

    /**
     * @param context context of the activity that uses this Renderer
     */
    GameRenderer(Context context) {
        this.context = context;
    }

    public void onSurfaceCreated(GL10 unused, EGLConfig config) {
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        shaderHandler = new ShaderHandler();
        ball = new Ball(0.5f, shaderHandler);
        ball2 = new Ball(0.5f, shaderHandler);
        rectangle1 = new Rectangle(2.0f, 0.2f, shaderHandler);
        rectangle1.setVelocity(10f, 10f);

        Matrix.setLookAtM(mViewMatrix, 0,
                0f, 0f, -3f,
                0f, 0f, 0f,
                0f, 1f, 0f);

        shaderHandler.setViewMatrix(mViewMatrix);
    }

    public void onDrawFrame(GL10 unused) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

        long time = SystemClock.uptimeMillis() % 6283;
        ball.setPos((float)sin((float) 4 * time / 1000), 0);
        ball2.setPos(0, (float)sin((float) 4 * time / 1000));
        ball2.setVelocity(50, 50);

        if (time % 10 == 1)
            SoundFXPlayer.playBallCollisionSound(context);


        ball.draw();
        ball2.draw();
        rectangle1.draw();
    }

    public void onSurfaceChanged(GL10 unused, int width, int height) {
        GLES20.glViewport(0, 0, width, height);

        float ratio = (float) width / height;
        Utils.ySize = Utils.xSize / ratio;

        Matrix.frustumM(mProjectionMatrix, 0, 0, Utils.xSize, 0, Utils.ySize, 2.9f, 10);
        shaderHandler.setProjectionMatrix(mProjectionMatrix);
    }



}


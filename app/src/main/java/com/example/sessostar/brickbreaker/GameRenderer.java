package com.example.sessostar.brickbreaker;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.Shader;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.Matrix;
import android.os.Build;
import android.os.SystemClock;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.LinkedList;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import static java.lang.Math.sin;

/**
 * Created by user on 20/05/17.
 */

public class GameRenderer implements Renderer{

    private Context context;

    GameLevel level;
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
        Matrix.setLookAtM(mViewMatrix, 0,
                0f, 0f, -3f,
                0f, 0f, 0f,
                0f, 1f, 0f);
        shaderHandler.setViewMatrix(mViewMatrix);
    }


    public void onDrawFrame(GL10 unused) {
        GLES20.glClearColor(.5f, .6f, 8f,1f);
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

        level.checkColisions();
        level.draw();
    }

    public void onSurfaceChanged(GL10 unused, int width, int height) {
        float ratio = (float) width / height;
        Utils.ySize = Utils.xSize / ratio;
        level = new GameLevel(this.context, shaderHandler, 5);

        GLES20.glViewport(0, 0, width, height);
        Matrix.orthoM(mProjectionMatrix, 0, 0, Utils.xSize, 0, Utils.ySize, -2, 10);
        shaderHandler.setProjectionMatrix(mProjectionMatrix);
    }

    public void touchRight() {
        level.movePaddleRight();
    }

    public void touchLeft() {
        level.movePaddleLeft();
    }

    public void touchUp() {
        level.stopPaddle ();
    }



}


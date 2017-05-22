package com.example.sessostar.brickbreaker;

import android.opengl.GLES20;
import android.opengl.Matrix;

import java.nio.Buffer;

/**
 * Created by user on 21/05/17.
 */

public class ShaderHandler {
    public int program;
    private int positionHandle;
    private int colorHandle;

    private float[] viewMatrix = new float[16];
    private float[] projectionMatrix = new float[16];
    private float[] modelMatrix = new float[16];

    private final String vertexShaderCode =
            "uniform mat4 uViewMatrix;" +
            "uniform mat4 uProjectionMatrix;" +
            "uniform mat4 uModelMatrix;" +
            "attribute vec4 vPosition;" +
            "void main() {" +
            "  gl_Position = uProjectionMatrix * uModelMatrix * uViewMatrix * vPosition;" +
            "}";

    private final String fragmentShaderCode =
            "precision mediump float;" +
                    "uniform vec4 vColor;" +
                    "void main() {" +
                    "  gl_FragColor = vColor;" +
                    "}";

    /**
     * Constructor
     */
    public ShaderHandler() {
        int vertexShader = loadShader(GLES20.GL_VERTEX_SHADER, vertexShaderCode);
        int fragmentShader = loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode);

        program = GLES20.glCreateProgram();
        GLES20.glAttachShader(program, vertexShader);
        GLES20.glAttachShader(program, fragmentShader);
        GLES20.glLinkProgram(program);

        Matrix.setIdentityM(viewMatrix, 0);
        Matrix.setIdentityM(projectionMatrix, 0);
        Matrix.setIdentityM(modelMatrix, 0);

        useProgram();
        loadViewMatrix();
        loadProjectionMatrix();
        loadModelMatrix();

    }


    /************** GETTERS / SETTERS *****************/
    /*
        View Matrix
     */
    public float[] getViewMatrix() {
        return viewMatrix;
    }

    public void setViewMatrix(float[] vm) {
        viewMatrix = vm;
        loadViewMatrix();
    }

    /*
        Projection Matrix
     */
    public float[] getProjectionMatrix() {
        return projectionMatrix;
    }

    public void setProjectionMatrix(float[] pm) {
        projectionMatrix = pm;
        loadProjectionMatrix();
    }

    /*
        Model Matrix
     */
    public float[] getModelMatrix() {
        return modelMatrix;
    }

    public void setModelMatrix(float[] mm) {
        modelMatrix = mm;
        loadModelMatrix();
    }

    /***************** CLASS METHODS *****************/

    /**
     * Loads points of buffer on vertices
     * @param size number of coordinates per vertex
     * @param stride number of bytes per vertex
     * @param ptr buffer of vertices
     */
    public void setPosition(int size, int stride, Buffer ptr) {
        int positionHandle = GLES20.glGetAttribLocation(program, "vPosition");
        GLES20.glEnableVertexAttribArray(positionHandle);
        GLES20.glVertexAttribPointer(positionHandle, size,
                GLES20.GL_FLOAT, false,
                stride, ptr);
    }

    /**
     * Loads color on fragment
     * @param color
     */
    public void setColor(float[] color) {
        colorHandle = GLES20.glGetUniformLocation(program, "vColor");
        GLES20.glUniform4fv(colorHandle, 1, color, 0);
    }

    /**
     * Disables the position handle
     */
    public void disablePositionHandle() {
        GLES20.glDisableVertexAttribArray(positionHandle);
    }

    /**
     * Enables this handle program
     */
    public void useProgram() {
        GLES20.glUseProgram(program);
    }

    public void loadViewMatrix() {
        int vMatrixHandle = GLES20.glGetUniformLocation(program, "uViewMatrix");
        GLES20.glUniformMatrix4fv(vMatrixHandle, 1, false, viewMatrix, 0);
    }

    public void loadProjectionMatrix() {
        int pMatrixHandle = GLES20.glGetUniformLocation(program, "uProjectionMatrix");
        GLES20.glUniformMatrix4fv(pMatrixHandle, 1, false, projectionMatrix, 0);
    }

    public void loadModelMatrix() {
        int mMatrixHandle = GLES20.glGetUniformLocation(program, "uModelMatrix");
        GLES20.glUniformMatrix4fv(mMatrixHandle, 1, false, modelMatrix, 0);
    }

    /**
     * Loads shader
     * @param type
     * @param shaderCode
     * @return shader handle
     */
    public int loadShader(int type, String shaderCode){
        int shader = GLES20.glCreateShader(type);

        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);

        return shader;
    }
}

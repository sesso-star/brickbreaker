package com.example.sessostar.brickbreaker;

import android.content.Context;
import android.opengl.GLSurfaceView;

/**
 * Created by user on 20/05/17.
 */

public class GameView extends GLSurfaceView {
    private final GameRenderer gameRenderer;

    public GameView(Context context) {
        super(context);

        setEGLContextClientVersion(2);
        gameRenderer = new GameRenderer();

        setRenderer(gameRenderer);
//        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }
}

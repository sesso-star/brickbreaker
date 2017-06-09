package com.example.sessostar.brickbreaker;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

/**
 * Created by user on 20/05/17.
 */

public class GameView extends GLSurfaceView {
    private final GameRenderer gameRenderer;
    private final GameActivity gameActivity;
    boolean gamePaused;

    public GameView(Context context) {
        super(context);

        gamePaused = true;
        Utils.stopTime();

        gameActivity = (GameActivity) context;
        setEGLContextClientVersion(2);
        gameRenderer = new GameRenderer(context);
        setRenderer(gameRenderer);
//        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        // MotionEvent reports input details from the touch screen
        // and other input controls. In this case, you are only
        // interested in events where the touch position changed.

        float x = e.getX();
        float y = e.getY();

        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (gamePaused) {
                    gameActivity.hideTextView();
                    Utils.unStopTime();
                }

                if (x < getWidth() / 2) {
                    gameRenderer.touchLeft();
                }
                else {
                    gameRenderer.touchRight();
                }
                break;
            case MotionEvent.ACTION_UP:
                gameRenderer.touchUp();
                break;
        }

        return true;
    }
}

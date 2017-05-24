package com.example.sessostar.brickbreaker;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.SoundEffectConstants;

/**
 * Created by user on 20/05/17.
 */

public class GameActivity extends Activity {
    private GLSurfaceView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SoundFXPlayer.playGameSong(this);
        gameView = new GameView(this);
        setContentView(gameView);
    }

    @Override
    protected void onPause() {
        super.onPause();

        SoundFXPlayer.pauseGameSong();
    }

    @Override
    protected void onResume() {
        super.onResume();

        SoundFXPlayer.playGameSong(this);
    }
}

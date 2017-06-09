package com.example.sessostar.brickbreaker;

import android.app.Activity;
import android.graphics.Color;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.SoundEffectConstants;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by user on 20/05/17.
 */

public class GameActivity extends Activity {
    private GLSurfaceView gameView;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);

        SoundFXPlayer.playGameSong(this);
        gameView = new GameView(this);
        setContentView(gameView);

        mTextView = new TextView(this);
        mTextView.setText("Touch to start");
        mTextView.setTextColor(Color.WHITE);
        mTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 32);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;
        addContentView(mTextView, params);
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

    public void hideTextView() {
        mTextView.setVisibility(View.INVISIBLE);
    }

    public void setTextViewText(String text) {
        mTextView.setText(text);
        mTextView.setVisibility(View.VISIBLE);
    }
}

package com.example.sessostar.brickbreaker;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;

import java.io.IOException;

/**
 * Created by gustavo on 5/23/17.
 */

public class SoundFXPlayer {

    private static class MyFXPlayer extends MediaPlayer {
        // -1 - being prepared
        // 0  - not prepared
        // 1  - prepared
        private int state;

        @Override
        public void prepareAsync() {
            super.prepareAsync();
            super.setOnPreparedListener(new OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    state = 1;
                }
            });
            state = -1;
        }

        public boolean isPrepared() {
            return state == 1;
        }
    }

    private static MyFXPlayer ballCollision;
    private static MediaPlayer gameSound;

    static void prepareFXes(Context context) {
        ballCollision = new MyFXPlayer();
        Uri soundUri = Uri.parse("android.resource://com.example.sessostar.brickbreaker/" + R.raw.ball_collision_sound);
        try {
            ballCollision.setDataSource(context, soundUri);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ballCollision.prepareAsync();
    }

    static void playBallCollisionSound() {
        if (ballCollision.isPrepared())
            ballCollision.start();
    }
}

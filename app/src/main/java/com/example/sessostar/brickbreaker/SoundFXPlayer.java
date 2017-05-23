package com.example.sessostar.brickbreaker;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by gustavo on 5/23/17.
 */

public class SoundFXPlayer {

    private class MyFXPlayer extends MediaPlayer {
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

    private static MediaPlayer ballCollision;
    private static MediaPlayer gameSound;

    static void prepareFXes(Context context) {
        ballCollision = new MediaPlayer();
        ballCollision = MediaPlayer.create(context, R.raw.ball_collision_sound);
        ballCollision.prepareAsync();
    }

    static void playBallCollisionSound() {
        ballCollision.start();
    }
}

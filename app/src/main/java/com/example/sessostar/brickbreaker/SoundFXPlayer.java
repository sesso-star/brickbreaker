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

        public MyFXPlayer() {
            super();

            super.setOnPreparedListener(new OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.start();
                }
            });

            super.setOnCompletionListener(new OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.release();
                }
            });
        }
    }

    private static MediaPlayer gameSound;

    static void prepareFXes(Context context) {
    }

    static void playBallCollisionSound(Context context) {
        MyFXPlayer fxPlayer = new MyFXPlayer();
        Uri soundUri = Uri.parse("android.resource://com.example.sessostar.brickbreaker/" + R.raw.ball_collision_sound);
        try {
            fxPlayer.setDataSource(context, soundUri);
        } catch (IOException e) {
            e.printStackTrace();
        }
        fxPlayer.prepareAsync();
    }
}

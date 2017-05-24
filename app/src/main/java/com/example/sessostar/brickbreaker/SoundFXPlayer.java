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

        /*
        * song is not being played if state = 0
        * song is being played     if state = 1
        * song is paused           if state = 2
        * */
        private int state = 0;

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

    static void playGameSong(Context context) {
        if (gameSound == null)
            createGameSound(context);
        else if (!gameSound.isPlaying())
            gameSound.start();
    }

    private static void createGameSound(Context context) {
        gameSound = new MediaPlayer();
        Uri soundUri = Uri.parse("android.resource://com.example.sessostar.brickbreaker/" +
                R.raw.main_song);
        try {
            gameSound.setDataSource(context, soundUri);
        } catch (IOException e) {
            e.printStackTrace();
        }
        gameSound.prepareAsync();
        gameSound.setLooping(true);
        gameSound.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });
    }

    static void pauseGameSong() {
        gameSound.pause();
    }

    static void playBallCollisionSound(Context context) {
        MyFXPlayer fxPlayer = new MyFXPlayer();
        Uri soundUri = Uri.parse("android.resource://com.example.sessostar.brickbreaker/" + R.raw.collision_sound);
        try {
            fxPlayer.setDataSource(context, soundUri);
        } catch (IOException e) {
            e.printStackTrace();
        }
        fxPlayer.prepareAsync();
    }
}

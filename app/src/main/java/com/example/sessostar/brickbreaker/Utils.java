package com.example.sessostar.brickbreaker;

/**
 * Created by user on 22/05/17.
 */

class Utils {
    static final float xSize = 10f;
    static float dt = 0.001f;
    static float ySize;

    public static void stopTime() {
        dt = 0;
    }

    public static void unStopTime() {
        dt = 0.001f;
    }
}

package com.example.sessostar.brickbreaker;

import android.util.Log;

import java.util.LinkedList;
import java.util.Random;

import static java.lang.Math.PI;

/**
 * Created by gustavo on 6/9/17.
 */

public class MovingBrickColumn {
    private ShaderHandler shaderHandler;
    private LinkedList<MovingBrick> brickList;


    MovingBrickColumn(int n, ShaderHandler sh) {
        shaderHandler = sh;
        float heightRatio = 0.2f;
        float lateralMargins = Utils.xSize * .1f;
        float brickWidth = (Utils.xSize - 2 * lateralMargins) * .2f;
        float brickHeight = brickWidth * heightRatio;
        float amplitude = (Utils.xSize - lateralMargins) / 2 - brickWidth / 2;
        float topOffset = 0.25f;
        float heightSpace = .5f * brickHeight;
        Random rand = new Random();

        brickList = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            float period = 0.05f + (float) (rand.nextInt(11) / 500 - 0.01f);
            Log.d("MovingBrickColumn", "period: " + period + "\n");
            MovingBrick b = new MovingBrick(brickWidth, brickHeight, amplitude,
                    .05f, shaderHandler);
            b.setDefense(5);
            b.setPos(Utils.xSize / 2,
                    Utils.ySize * (1 - topOffset) - i * heightSpace - (i - 1) * brickHeight);
            b.setPhase((float) (rand.nextFloat() * 2 * PI));
            brickList.add(b);
        }
    }


    Boolean checkColisionsWith(Ball ball) {
        Boolean collided = false;
        for (int i = 0; i < brickList.size(); i++) {
            if (ball.checkColisionWith(brickList.get(i))) {
                if (brickList.get(i).hit())
                    brickList.remove(i);
                collided = true;
            }
        }
        return collided;
    }


    void drawBricks() {
        for (Brick brick : brickList) {
            brick.draw();
        }
    }

}

package com.example.sessostar.brickbreaker;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

/**
 * Created by user on 28/05/17.
 */

class BrickGrid {
    private ShaderHandler shaderHandler;
    private LinkedList<Brick> brickList;

    BrickGrid(int nx, int ny, ShaderHandler sh) {
        shaderHandler = sh;
        float offSetRatio = 0.3f;
        float spacingRatio = 0.1f;
        float heightRatio = 0.3f;
        float brickWidth = (Utils.xSize - ((nx - 1) * spacingRatio + nx * offSetRatio) * (Utils.xSize / nx)) /  nx;
        float brickHeight = heightRatio * brickWidth;
        float spacing = spacingRatio * (Utils.xSize / nx);
        float offSet = offSetRatio * (Utils.xSize ) / 2;

        brickList = new LinkedList<Brick>();
        for (int i = 0; i < nx; i++) {
            for (int j = 0; j < ny; j++) {
                Brick brick = new Brick(brickWidth, 0.2f, shaderHandler);
                brick.setPos(offSet + (brickWidth / 2) + i * (spacing + brickWidth),
                        Utils.ySize - (offSet + (brickHeight / 2) + j * (spacing + brickHeight)));
                brick.setDefense(2);
                brickList.add(brick);
            }
        }
    }

    void hardenRandomBricks(int n) {
        int nBricks = brickList.size();
        if (nBricks < n)
            return;
        Random rand = new Random();
        while (nBricks > 0) {
            int randomIndex = rand.nextInt(nBricks);
            brickList.get(randomIndex).addDefense(1);
            Collections.swap(brickList, randomIndex, nBricks - 1);
            nBricks--;
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

    boolean isEmpty() {
        return brickList.isEmpty();
    }

}

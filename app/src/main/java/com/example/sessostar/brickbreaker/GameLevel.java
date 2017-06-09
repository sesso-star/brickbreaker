package com.example.sessostar.brickbreaker;

import android.content.Context;

/**
 * Created by gustavo on 6/9/17.
 */

class GameLevel {

    Context context;
    private BrickGrid brickGrid;
    private ShaderHandler shaderHandler;
    private int difficulty;
    Ball ball;
    Paddle paddle;
    MovingBrick movingBrick;
    RoomWall roomWall;

    /**
     * @param sh is the ShaderHandler
     * @param difficulty determines how hard this game level is
     */
    GameLevel(Context context, ShaderHandler sh, int difficulty) {
        this.context = context;
        this.shaderHandler = sh;
        this.difficulty = difficulty;

        this.brickGrid = new BrickGrid (10, 6, sh);

        paddle = new Paddle(2f, 0.2f, sh);
        paddle.setPos(5f, 1f);

        ball = new Ball(0.25f, sh);
        ball.setPos(4f, 4f);
        ball.setVelocity(-75, -75);

        movingBrick = new MovingBrick(2f, 0.2f, 4f, 0.05f, sh);
        movingBrick.setPos(5f, 5f);

        roomWall = new RoomWall(Utils.xSize, Utils.ySize, sh);
    }

    void checkColisions () {
        if (brickGrid.checkColisionsWith(ball))
            SoundFXPlayer.playBallCollisionSound(context);

        if (ball.checkColisionWith(paddle))
            SoundFXPlayer.playBallCollisionSound(context);

        if (ball.checkColisionWith(movingBrick))
            SoundFXPlayer.playBallCollisionSound(context);

        if (paddle.checkColisionWith(roomWall))
            SoundFXPlayer.playBallCollisionSound(context);

        if (roomWall.checkColisionsWith(ball))
            SoundFXPlayer.playBallCollisionSound(context);
    }

    void draw () {
        brickGrid.drawBricks();
        ball.draw();
        paddle.draw();
        movingBrick.draw();
        roomWall.draw();
    }

    void movePaddleRight () {
        paddle.setVelocity(70f, 0f);
    }

    void movePaddleLeft () {
        paddle.setVelocity(-70f, 0f);
    }

    void stopPaddle () {
        paddle.setVelocity(0f, 0f);
    }

}

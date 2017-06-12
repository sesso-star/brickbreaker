package com.example.sessostar.brickbreaker;

import android.content.Context;

/**
 * Created by gustavo on 6/9/17.
 */

class GameLevel {

    private Context context;
    private BrickGrid brickGrid;
    private ShaderHandler shaderHandler;
    private int difficulty;
    private Ball ball;
    private Paddle paddle;
    private float paddleSpeed;

    private MovingBrickColumn movingBrickColumn;
    private RoomWall roomWall;

    /**
     * @param sh is the ShaderHandler
     * @param difficulty determines how hard this game level is.
     *                   It should be a number between 0 and 9.
     */
    GameLevel(Context context, ShaderHandler sh, int difficulty) {
        this.context = context;
        this.shaderHandler = sh;
        this.difficulty = difficulty;

        paddleSpeed = 50 * (float) difficulty / 9 + 70;
        paddle = new Paddle(2f, 0.2f, sh);
        paddle.setPos(5f, 1f);

        float ballSpeed = (75 / 9) * (float) difficulty + 75;
        ball = new Ball(0.25f, sh);
        ball.setPos(5f, 1.36f);
        ball.setVelocity(ballSpeed, ballSpeed);

        int nx = (int) (3 * (float) difficulty / 9 + 4);
        int ny = (int) (4 * (float) difficulty / 9 + 2);
        int mb = (int) (5 * (float) difficulty / 9 + 0);
        int eh = nx * ny * difficulty;
        this.brickGrid = new BrickGrid (nx, ny, sh);
        this.brickGrid.hardenRandomBricks(eh);
        movingBrickColumn = new MovingBrickColumn(mb, shaderHandler);

        roomWall = new RoomWall(Utils.xSize, Utils.ySize, sh);
    }

    void checkColisions () {
        if (brickGrid.checkColisionsWith(ball))
            SoundFXPlayer.playBallCollisionSound(context);

        if (movingBrickColumn.checkColisionsWith(ball))
            SoundFXPlayer.playBallCollisionSound(context);

        if (ball.checkColisionWith(paddle))
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
        movingBrickColumn.drawBricks();
        roomWall.draw();
    }

    public boolean isOver() {
        return brickGrid.isEmpty() && movingBrickColumn.isEmpty();
    }

    void movePaddleRight () {
        paddle.setVelocity(paddleSpeed, 0f);
    }

    void movePaddleLeft () {
        paddle.setVelocity(-paddleSpeed, 0f);
    }

    void stopPaddle () {
        paddle.setVelocity(0f, 0f);
    }

    Boolean checkBallExited () {
        return ball.getPos().y < 0;
    }
}

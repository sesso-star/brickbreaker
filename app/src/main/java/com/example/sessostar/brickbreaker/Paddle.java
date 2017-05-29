package com.example.sessostar.brickbreaker;

/**
 * Created by user on 28/05/17.
 */

public class Paddle extends Rectangle {
    public Paddle(float width, float height, ShaderHandler sh) {
        super(width, height, sh);
    }

    public boolean checkColisionWith(Rectangle rec) {
        Vector2D pos = this.pos.plus(velocity.times(Utils.dt));

        Position ul = new Position(pos.x - width / 2f, pos.y - height / 2f);
        Position bl = new Position(pos.x - width / 2f, pos.y + height / 2f);
        Position br = new Position(pos.x + width / 2f, pos.y + height / 2f);
        Position ur = new Position(pos.x + width / 2f, pos.y - height / 2f);
        Position[] vertices = {ul, bl, br, ur};

        Position recPos = rec.getPos();
        float recWidth = rec.getWidth();
        float recHeight = rec.getHeight();

        for (Position vertex : vertices) {
            if (vertex.x > recPos.x - recWidth / 2 && vertex.x < recPos.x + recWidth / 2 &&
                    vertex.y > recPos.y - recHeight / 2 && vertex.y < recPos.y + recHeight / 2) {
                setVelocity(0, 0);
                return true;
            }
        }
        return false;
    }

    public boolean checkColisionWith(RoomWall room) {
        for (Rectangle wall : room.getWalls()) {
            if (checkColisionWith(wall))
                return true;
        }
        return false;
    }
}

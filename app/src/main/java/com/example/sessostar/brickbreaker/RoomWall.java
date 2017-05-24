package com.example.sessostar.brickbreaker;

/**
 * Created by gustavo on 5/24/17.
 */

public class RoomWall {
    private Rectangle[] walls;
    private final float wallThickness = .2f;
    private final float wallColor[] = {.3f, .4f, 0.6f, 1.0f};


    /**
     * @param roomHeight is the size of the y axis of the screen
     * @param roomWidth is the size of the x axis of the screen
     * @param sh is the ShaderHandler that will draw the walls */
    RoomWall(float roomWidth, float roomHeight, ShaderHandler sh) {
        walls = new Rectangle[4];
        walls[0] = new Rectangle(roomWidth, wallThickness, sh);
        walls[2] = new Rectangle(roomWidth, wallThickness, sh);
        walls[1] = new Rectangle(wallThickness, roomHeight, sh);
        walls[3] = new Rectangle(wallThickness, roomHeight, sh);

        walls[0].setPos(Utils.xSize / 2, wallThickness / 2);
        walls[2].setPos(Utils.xSize / 2, Utils.ySize - wallThickness / 2);
        walls[1].setPos(Utils.xSize - wallThickness / 2, Utils.ySize / 2);
        walls[3].setPos(wallThickness / 2, Utils.ySize / 2);

        for (Rectangle wall : walls)
            wall.setColor(wallColor);
    }


    Rectangle[] getWalls() {
        return walls;
    }

    /**
     * Draws room walls */
    void draw() {
        for (Rectangle wall : walls)
            wall.draw();
    }
}
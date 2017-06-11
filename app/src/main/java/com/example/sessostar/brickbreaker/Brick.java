package com.example.sessostar.brickbreaker;

/**
 * Created by user on 28/05/17.
 */

public class Brick extends Rectangle {
    private int defense;
    private int maxDefense;
    private float[] defenseColor1 = {255 / 255f, 112 / 255f, 77 / 255f};
    private float[] defenseColor2 = {255 / 255f, 219 / 255f, 77 / 255f};
    private float[] defenseColor3 = {255 / 255f, 255 / 255f, 128 / 255f};
    private float[] defenseColor4 = {204 / 255f, 255 / 255f, 102 / 255f};
    private float[] defenseColor5 = {159 / 255f, 255 / 255f, 128 / 255f};
    private float[][] defenseColors = {defenseColor1, defenseColor2, defenseColor3, defenseColor4,
            defenseColor5};

    public Brick(float width, float height, ShaderHandler sh) {
        super(width, height, sh);

        defense = 1;
        maxDefense = defense;
        adjustColor();
    }

    public void setDefense(int d) {
        defense = d;
        if (defense > 5)
            defense = 5;
        maxDefense = defense;
        adjustColor();
    }

    public void addDefense(int d) {
        setDefense(d + defense);
    }

    public int getDefense() {
        return defense;
    }

    /**
     * Changes the brick color according to the number of defenses it has left
     * @return true if it is destroyed or false otherwise
     */
    public boolean hit() {
        if (--defense == 0)
            return true;
        adjustColor();
        return false;
    }

    private void adjustColor() {
        float[] color =  new float[] {
                defenseColors[defense - 1][0],
                defenseColors[defense - 1][1],
                defenseColors[defense - 1][2],
                1.0f
        };
        setColor(color);
    }

}

package com.example.sessostar.brickbreaker;

/**
 * Created by user on 28/05/17.
 */

public class Brick extends Rectangle {
    private int defense;

    public Brick(float width, float height, ShaderHandler sh) {
        super(width, height, sh);

        defense = 1;
        adjustColor();
    }

    public void setDefense(int d) {
        defense = d;
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
        if (defense > 1)
            setColor(new float[] {0.9f, 0.2f, 0.3f, 1.0f});
        else
            setColor(new float[] {0.5f, 0.1f, 0.1f, 1.0f});
    }

}

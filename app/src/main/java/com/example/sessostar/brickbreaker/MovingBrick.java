package com.example.sessostar.brickbreaker;

import static java.lang.Math.cos;
import static java.lang.StrictMath.PI;

/**
 * Created by user on 29/05/17.
 */

public class MovingBrick extends Brick {
    private float t;
    private float amp;
    private float period;

    public MovingBrick(float width, float height, float amp, float period, ShaderHandler sh) {
        super(width, height, sh);

        this.amp = amp;
        this.period = period;

        t = 0;
    }

    public void draw() {
        setVelocity((float) cos(t / period) * amp / period, 0f);
        t = (t + Utils.dt) > 2 * PI ? 0 : t + Utils.dt;
        super.draw();
    }
}

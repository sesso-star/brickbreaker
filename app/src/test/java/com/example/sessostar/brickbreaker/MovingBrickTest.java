package com.example.sessostar.brickbreaker;

import org.junit.Test;

import static java.lang.Math.PI;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by gustavo on 6/11/17.
 */

public class MovingBrickTest {

    @Test
    public void Position_onPhaseSet_ShouldChange() throws Exception {
        float amp = 3f;
        float period = .01f;
        MovingBrick mb = new MovingBrick(10, 10, amp, period, null);
        mb.setPhase((float) PI);
        assertEquals(0f, mb.getPos().x, 1e-2);
        mb.setPhase(0f);
        mb.setPos(0, 0);
        mb.setPhase(0.1f);
        assertEquals(-1.63f, mb.getPos().x, 1e-2);
    }

}

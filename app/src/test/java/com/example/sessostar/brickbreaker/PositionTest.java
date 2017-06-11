package com.example.sessostar.brickbreaker;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by gustavo on 6/11/17.
 */

public class PositionTest {

    @Test
    public void Position_onTime_ShoulBeUpdated() throws Exception {
        Position p = new Position(0f, 0f);
        Velocity v = new Velocity(1f, 100f);
        float dt = 1f;
        p.updatePosition(v, dt);
        assertThat(p.x, is(1f));
        assertThat(p.y, is(100f));
    }

}

package com.example.sessostar.brickbreaker;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by gustavo on 6/11/17.
 */

public class BallTest {
    @Test
    public void Position_onSet_ShouldChange() throws Exception {
        Ball b = new Ball(5f, null);
        b.setPos(13f, 13f);
        assertThat(b.getPos().x, is(13f));
        assertThat(b.getPos().y, is(13f));
    }

    @Test
    public void Velocity_onSet_ShouldChange() throws Exception {
        Ball b = new Ball(5f, null);
        b.setVelocity(13f, 13f);
        assertThat(b.getVelocity().x, is(13f));
        assertThat(b.getVelocity().y, is(13f));
    }

    @Test
    public void whenNearRectangle_ShouldColide() throws Exception {
        Ball b = new Ball(5f, null);
        b.setPos(0f, 0f);
        Rectangle r = new Rectangle(.01f, .01f, null);
        r.setPos(3.5f, 3.5f);
        assertThat(b.checkColisionWith(r), is(true));
        r.setPos(3.6f, 3.6f);
        assertThat(b.checkColisionWith(r), is(false));
    }
}
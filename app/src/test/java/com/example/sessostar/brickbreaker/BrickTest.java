package com.example.sessostar.brickbreaker;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;


/**
 * Created by gustavo on 6/11/17.
 */

public class BrickTest {

    @Test
    public void Defense_onAdd_ShouldGrow() throws Exception {
        Brick brick = new Brick(10f, 10f, null);
        brick.setDefense(10);
        brick.addDefense(10);
        assertThat(brick.getDefense(), is(20));
    }


    @Test
    public void Defense_onHit_ShouldDecrease() throws Exception {
        Brick brick = new Brick(10f, 10f, null);
        brick.setDefense(10);
        brick.hit();
        assertThat(brick.getDefense(), is(9));
    }


    @Test
    public void Defense_onHitAllDefense_ShouldBeZero() throws Exception {
        int defense = 10;
        Brick brick = new Brick(10f, 10f, null);
        brick.setDefense(defense);
        for (int i = 0; i < defense - 1; i++)
            brick.hit();
        boolean dieded = brick.hit();
        assertThat(brick.getDefense(), is(0));
        assertThat(dieded, is(true));
    }

}

package com.example.sessostar.brickbreaker;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by gustavo on 6/11/17.
 */

public class RoomWallTest {

    @Test
    public void Walls_ShouldContainBall() throws Exception {
        RoomWall roomWall = new RoomWall(20f, 20f, null);
        Ball b = new Ball(5f, null);
        b.setPos(0f, 0f);
        assertThat(roomWall.checkColisionsWith(b), is (true));
        b.setPos(20f, 0f);
        assertThat(roomWall.checkColisionsWith(b), is (true));
        b.setPos(0f, 20f);
        assertThat(roomWall.checkColisionsWith(b), is (true));
        b.setPos(20f, 20f);
        assertThat(roomWall.checkColisionsWith(b), is (true));
        b.setPos(10f, 10f);
        assertThat(roomWall.checkColisionsWith(b), is (false));
    }

}

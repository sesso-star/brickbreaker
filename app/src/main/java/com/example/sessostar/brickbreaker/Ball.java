package com.example.sessostar.brickbreaker;

import android.opengl.Matrix;
import android.os.SystemClock;

import java.util.Vector;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.StrictMath.abs;


/**
 * Created by user on 21/05/17.
 */

public class Ball {
    private Circle circle;
    private Position pos;
    private Velocity velocity;
    private float radius;

    ShaderHandler shaderHandler;

    public Ball(float radius, ShaderHandler sh) {
        this.radius = radius;
        shaderHandler = sh;

        circle = new Circle(radius, 40, sh);
        pos = new Position(0, 0);
        velocity = new Velocity(0, 0);
    }

    /*************** GETTERS / SETTERS *****************/

    public Position getPos() {
        return pos;
    }

    public void setPos(float x, float y) {
        pos.x = x;
        pos.y = y;
    }

    public Velocity getVelocity() {
        return velocity;
    }

    public void setVelocity(float vx, float vy) {
        velocity.x = vx;
        velocity.y = vy;
    }

    /*************** CLASS METHODS *****************/


    /**
     * Checks colision with rectangle and update the position and velocity
     * @param rec a rectangle
     * @return true if there was a colision and false otherwise
     */
    public boolean checkColisionWith(Rectangle rec) {
        Vector2D ballUp     = pos.plus(new Vector2D(0, radius));
        Vector2D ballLeft   = pos.plus(new Vector2D(-radius, 0));
        Vector2D ballDown   = pos.plus(new Vector2D(0, -radius));
        Vector2D ballRight  = pos.plus(new Vector2D(radius, 0));
        Vector2D vt = velocity.times(Utils.dt);

        Position recPos = rec.getPos();
        float hw = rec.getWidth() / 2;
        float hh = rec.getHeight() / 2;
        Position upperLeft   = new Position(recPos.x - hw, recPos.y + hh);
        Position upperRight  = new Position(recPos.x + hw, recPos.y + hh);
        Position bottomLeft  = new Position(recPos.x - hw, recPos.y - hh);
        Position bottomRight = new Position(recPos.x + hw, recPos.y - hh);

        Position upColision     = intersects(ballDown, vt, upperRight, upperLeft.minus((upperRight)));
        Position leftColision   = intersects(ballRight, vt, upperLeft, bottomLeft.minus((upperLeft)));
        Position downColision   = intersects(ballUp, vt, bottomLeft, bottomRight.minus((bottomLeft)));
        Position rightColision  = intersects(ballLeft, vt, bottomRight, upperRight.minus((bottomRight)));

        boolean cornerColision = false;
        cornerColision = cornerColision || upperLeft.minus(pos).norm() < radius;
        cornerColision = cornerColision || upperRight.minus(pos).norm() < radius;
        cornerColision = cornerColision || bottomLeft.minus(pos).norm() < radius;
        cornerColision = cornerColision || bottomRight.minus(pos).norm() < radius;


        if (upColision == null && leftColision == null &&
                downColision == null && rightColision == null &&
                !cornerColision)
            return false;


        if (upColision != null || downColision != null)
            velocity.y *= -1;
        else if (leftColision != null || rightColision != null)
            velocity.x *= -1;
        else if (upperLeft.minus(pos).norm() < radius) {
            velocity.x = -abs(velocity.x);
            velocity.y = abs(velocity.y);
        } else if (upperRight.minus(pos).norm() < radius) {
            velocity.x = abs(velocity.x);
            velocity.y = abs(velocity.y);
        } else if (bottomLeft.minus(pos).norm() < radius) {
            velocity.x = -abs(velocity.x);
            velocity.y = -abs(velocity.y);
        } else if (bottomRight.minus(pos).norm() < radius) {
            velocity.x = abs(velocity.x);
            velocity.y = -abs(velocity.y);
        }
//        else if (cornerColision) {
//            velocity.x *= -1;
//            velocity.y *= -1;
//        }

        return true;
    }

    /**
     * Intersects two segments: p + q and q + s
     * @param p point of first segment
     * @param r vector that defines first segment
     * @param q point of second segment
     * @param s vector that defines second segment
     * @return the intersection point or null if there is none
     */
    private Position intersects(Vector2D p, Vector2D r, Vector2D q, Vector2D s) {
        float rxs = r.cross(s);
        if (rxs == 0)
            return null;

        float t = q.minus(p).cross(s) / rxs;
        float u = q.minus(p).cross(r) / rxs;

        if (t >= 0 && t <= 1 && u >= 0 && u <= 1) {
            Vector2D intersection = p.plus(r.times(t));
            return new Position(intersection.x, intersection.y);
        }

        return null;
    }

    public void draw() {
        pos.updatePosition(velocity, Utils.dt);

        float[] translationMatrix = new float[16];

        Matrix.setIdentityM(translationMatrix, 0);
        Matrix.translateM(translationMatrix, 0, pos.x, pos.y, 0);

        shaderHandler.setModelMatrix(translationMatrix);

        circle.draw();
    }
}

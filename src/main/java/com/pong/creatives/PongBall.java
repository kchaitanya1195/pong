package com.pong.creatives;

import com.pong.Constants;
import com.pong.physics.Vector;

public class PongBall {
    public Vector position, speed;

    public PongBall(int x, int y) {
        position = new Vector(x, y);
        speed = new Vector();
    }

    public void setXY(int x, int y) {
        if (x >= (Constants.PPanel.width - Constants.PBall.diameter) || x <= 0)
            speed.x = -speed.x;
        if (y >= (Constants.PPanel.height - Constants.PBall.diameter) || y <= 0)
            speed.y = -speed.y;
        position.setXY(x, y);
    }
    public void setSpeedXY(long speedX, long speedY) {
        this.speed.setXY(speedX, speedY);
    }
    public void setSpeed(Vector speed) {
        this.speed = speed;
    }

}

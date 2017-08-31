package com.pong.creatives;

import com.pong.Constants;
import com.pong.physics.Vector;

public class PongPaddle {
    private Vector position;
    public PongPaddle(int y) {
        position = new Vector(Constants.PPaddle.startPoxX, y);
    }

    public int getX() {
        return (int)Math.round(position.x);
    }
    public int getY() {
        return (int)Math.round(position.y);
    }
    public Vector getCenter() {
        return position.add(new Vector(Constants.PPaddle.width/2.0, Constants.PPaddle.height/2.0));
    }
    public void setX(int x) {
        if (x <= 0)
            this.position.x = 0;
        else if (x >= (Constants.PPanel.width - Constants.PPaddle.width))
            this.position.x = (Constants.PPanel.width - Constants.PPaddle.width);
        else
            this.position.x = x;
    }

}

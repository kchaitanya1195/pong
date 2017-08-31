package com.pong;

import com.pong.creatives.PongBall;
import com.pong.creatives.PongPaddle;
import com.pong.physics.Vector;
import com.pong.physics.VectorBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

class PongPanel extends JPanel {
    private PongBall ball;
    private PongPaddle playerOne, playerTwo;
    private Timer timer;
    PongPanel() {
        ball = new PongBall(Constants.PBall.startPoxX, Constants.PBall.startPosY);

        playerOne = new PongPaddle(Constants.PPaddle.startPos1Y);
        playerTwo = new PongPaddle(Constants.PPaddle.startPos2Y);

        setBackground(Color.BLACK);
        setLayout(null);
        setSize(new Dimension(Constants.PPanel.width, Constants.PPanel.height));

        //add(playerOne);
        //add(playerTwo);

        setupTimer();
        setupActionListeners();
    }

    private void setupActionListeners() {
        getInputMap().put(KeyStroke.getKeyStroke("SPACE"), Constants.ActionListenerKeys.startPong);
        getInputMap().put(KeyStroke.getKeyStroke("LEFT"), Constants.ActionListenerKeys.Move.One.left);
        getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), Constants.ActionListenerKeys.Move.One.right);

        getActionMap().put(Constants.ActionListenerKeys.startPong, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double direction = -Math.random() * Math.PI;

                ball.setSpeedXY(Math.round(Math.cos(direction) * Constants.Speed.speeds[0]), Math.round(Math.sin(direction) * Constants.Speed.speeds[0]));
            }
        });
        getActionMap().put(Constants.ActionListenerKeys.Move.One.left, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerOne.setX(playerOne.getX() - Constants.Speed.paddleSpeed);
            }
        });
        getActionMap().put(Constants.ActionListenerKeys.Move.One.right, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerOne.setX(playerOne.getX() + Constants.Speed.paddleSpeed);
            }
        });
    }
    private void setupTimer() {
        int delay = 1000/Constants.fps;
        timer = new Timer(delay, e -> {

            checkCollisionBetween(ball, playerOne);
            checkCollisionBetween(ball, playerTwo);

            int newX = (int)Math.round(ball.position.x + ball.speed.x * delay / Constants.fps);
            int newY = (int)Math.round(ball.position.y + ball.speed.y * delay / Constants.fps);

            ball.setXY(newX, newY);

            repaint();

            if(newX > getWidth() || newY > getHeight())
                timer.stop();
        });

        timer.start();
    }

    private void checkCollisionBetween(PongBall ball, PongPaddle player) {
        Rectangle intersection = SwingUtilities.computeIntersection(player.getX() - Constants.Collision.padding,
                                                                    player.getY() - Constants.Collision.padding,
                                                                    Constants.PPaddle.width + 2*Constants.Collision.padding,
                                                                    Constants.PPaddle.height + 2*Constants.Collision.padding,
                                                        new Rectangle((int)ball.position.x, (int)ball.position.y, Constants.PBall.diameter, Constants.PBall.diameter));

        if (intersection.width == 0 || intersection.height == 0)
            return;

        double surfaceX, surfaceY;
        double interCenterX = intersection.x + intersection.width / 2,
                interCenterY = intersection.y + intersection.height / 2;
        Vector playerCenter = player.getCenter();

        if (new Vector(interCenterX, playerCenter.y).subtract(new Vector(interCenterX, interCenterY)).dot(ball.speed) < 0) {
            return;
        }

        if (intersection.width < Constants.PBall.diameter) {
            if (interCenterX < playerCenter.x) {
                if (interCenterY <= playerCenter.y) {
                    surfaceX = intersection.width; surfaceY = -intersection.height;
                } else {
                    surfaceX = -intersection.width; surfaceY = -intersection.height;
                }
            } else {
                if (interCenterY <= playerCenter.y) {
                    surfaceX = intersection.width; surfaceY = intersection.height;
                } else {
                    surfaceX = -intersection.width; surfaceY = intersection.height;
                }
            }
        } else {
            if (interCenterX <= playerCenter.x) {
                if (interCenterY <= playerCenter.y) {
                    surfaceX = 1; surfaceY = -1;
                } else {
                    surfaceX = -1; surfaceY = -1;
                }
            } else {
                if (interCenterY <= playerCenter.y) {
                    surfaceX = 1; surfaceY = 1;
                } else {
                    surfaceX = -1; surfaceY = 1;
                }
            }
            surfaceX *= Constants.PPaddle.width / 2.0;
            surfaceY *= Constants.PPaddle.height / 2.0;
        }

        Vector surfaceNormal = new VectorBuilder(surfaceX, surfaceY).perpendicular().normalized().buildVector();

        ball.setSpeed(ball.speed.subtract(surfaceNormal.multiply(2 * ball.speed.dot(surfaceNormal))));

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(Color.WHITE);
        g2.fillOval((int)ball.position.x, (int)ball.position.y, Constants.PBall.diameter, Constants.PBall.diameter);

        g2.setColor(Color.WHITE);
        g2.fillRect(playerOne.getX(),playerOne.getY(), Constants.PPaddle.width, Constants.PPaddle.height);

        g2.setColor(Color.WHITE);
        g2.fillRect(playerTwo.getX(),playerTwo.getY(), Constants.PPaddle.width, Constants.PPaddle.height);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(Constants.PPanel.width, Constants.PPanel.height);
    }
}

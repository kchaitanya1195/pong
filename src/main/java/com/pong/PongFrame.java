package com.pong;

import javax.swing.*;

class PongFrame extends JFrame {
    PongFrame() {
        PongPanel panel = new PongPanel();
        add(panel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);

        setVisible(true);
    }
}

package com.pong;

import javax.swing.*;

public class Pong {
    public static void main (String[] args) {
        SwingUtilities.invokeLater(PongFrame::new);
    }
}

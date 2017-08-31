package com.pong;

public class Constants {
    private Constants() {}

    public static final int fps = 30;
    public static class PPanel {
        private PPanel() {}
        public static final int width = 400,
                                height = 700;
    }
    public static class PBall {
        private PBall() {}
        public static final int diameter = 50,
                startPoxX = ((PPanel.width - PBall.diameter) / 2),
                startPosY = ((PPanel.height - PBall.diameter) / 2);
    }
    public static class PPaddle {
        private PPaddle() {}
        public static final int width = 150,
                height = 15,
                startPoxX = ((PPanel.width - PPaddle.width) / 2),
                startPos2Y = 75,
                startPos1Y = PPanel.height - startPos2Y;
    }
    public static class ActionListenerKeys {
        private ActionListenerKeys() {}
        public static final String startPong = "startPong";
        public static class Move {
            private Move() {}
            public static class One {
                private One() {}
                public static final String left = "moveOneLeft",
                        right = "moveOneRight";
            }
        }
    }
    public static class Speed {
        private Speed() {}
        public static final int speedMultiplier = 7,
                paddleSpeed = 10;
        public static final int[] speeds = {speedMultiplier,
                                    speedMultiplier * 2,
                                    speedMultiplier * 4};
    }
    public static class Collision {
        private  Collision() {}
        public static final int padding = 5;
    }
}

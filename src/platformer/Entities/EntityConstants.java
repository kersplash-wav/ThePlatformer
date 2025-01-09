package platformer.Entities;

import java.awt.Color;

public class EntityConstants {

    public final class WorldSettings {
        public static final double worldGravity = 0.05;
    }

    public static final class CharacterConstants {
        public static final double width = 100;
        public static final double height = 150;
        public static final Color colour = Color.CYAN;
        public static final Color testColour = Color.MAGENTA;
    }

    // Settings for all platform types
    public static final class PlatformConstants {
        // friction (F) = coefficient of friction (μ) * normal force (N)
        // F = μN

        // Default platform
        public static final class StandardPlatform {
            public static final double width = 500;
            public static final double height = 12;
            public static final double friction = 10;
            public static final double bounce = 0;
            public static final Color colour = Color.GRAY;
        }

        // Rubber platform
        public static final class RubberPlatform {
            public static final double width = 500;
            public static final double height = 12;
            public static final double friction = 20;
            public static final double bounce = -5;
            public static final Color colour = Color.GREEN;
        }
    }
}
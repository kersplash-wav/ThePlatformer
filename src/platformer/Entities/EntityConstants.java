package platformer.Entities;

import java.awt.Color;

public class EntityConstants {

    public final class WorldConstants {
        public static final double worldGravity = 0.05;
    }

    public static final class CharacterConstants {
        public static final double width = 100;
        public static final double height = 150;
        public static final Color colour = Color.CYAN;
        public static final Color testColour = Color.MAGENTA;
        public static final double maxUpwardVelocity = -5;
        public static final double maxDownwardVelocity = 10;
        public static final double maxLeftwardVelocity = -10;
        public static final double maxRightwardVelocity = 10;
        public static final double upwardMovementVelocity = -1;
        public static final double downwardMovementVelocity = 1;
        public static final double rightWardMovementVelocity = 1;
        public static final double leftWardMovementVelocity = -1;
    }

    // Settings for all platform types
    public static final class PlatformConstants {
        // friction (F) = coefficient of friction (μ) * normal force (N)
        // F = μ

        public static class PresetPlatform {
            public final double width;
            public final double height;
            public final double friction;
            public final double bounce;
            public final Color colour;

            private PresetPlatform(double width, double height, double friction, double bounce, Color colour) {
                this.width = width;
                this.height = height;
                this.bounce = bounce;
                this.friction = friction;
                this.colour = colour;
            }
        }

        public static PresetPlatform StandardPlatform = new PresetPlatform(200, 12, 0.9, 0, Color.GRAY);
        public static PresetPlatform RubberPlatform = new PresetPlatform(200, 12, 1.1, -5, Color.GREEN);
        public static PresetPlatform EndPlatform = new PresetPlatform(200, 12, 0.9, 0, Color.PINK);
    }

    public static final class WallConstants {
        public static class PresetWall {
            public final double width;
            public final double height;
            public final double friction;
            public final double bounce;
            public final Color colour;

            private PresetWall(double width, double height, double friction, double bounce, Color colour) {
                this.width = width;
                this.height = height;
                this.bounce = bounce;
                this.friction = friction;
                this.colour = colour;
            }
        }

        public static PresetWall RubberWall = new PresetWall(12, 500, 0, 5, Color.GREEN);
        public static PresetWall StandardWall = new PresetWall(12, 500, 5, 0, Color.GRAY);
        public static PresetWall StickyWall = new PresetWall(12, 500, -20, 0, Color.ORANGE);
    }
}
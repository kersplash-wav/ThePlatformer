package platformer;

import java.awt.geom.Point2D;

public final class Constants {

    public final class WorldSettings {
        public static Point2D worldGravity = new Point2D.Double(0, 0.1);
    }

    public final class PlayerSettings {
        public static final int width = 100;
        public static final int height = 300;
        
    }

    // Settings for all platform types
    public final class Platform { 
        // Default platform
        public final class StandardPlatform {
            double friction = 1;
            double bounce = 1;
        }
        // Slippery ice platform
        public final class IcePlatform {
            double friction = 0;
            double bounce = 1;
        }
        // Fire platform
        public final class FirePlatform {
            double friction = 1;
            double bounce = 3;
            double dps = 10;
        }
        // Rubber platform
        public final class RubberPlatform{
            double friction = 2;
            double bounce = 5;
            
        }
    }
}

package platformer;

import java.awt.geom.Point2D;

public final class Constants {

    public final class WorldSettings {
        public static final double worldGravity = 0.1;
    }

    public final class PlayerSettings {
        public static final int width = 100;
        public static final int height = 150;
        
    }

    // Settings for all platform types
    public final class Platform { 

        //friction (F) = coefficient of friction (μ) * normal force (N)
        // F = μN

        // Default platform
        public final class StandardPlatform {
            
            public static final double friction = 1;
            public static final double bounce = 5;
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
            public final static double friction = 2;
            public final static double bounce = 20;
        }
    }
}

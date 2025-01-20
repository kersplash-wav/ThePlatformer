// File Directory //
package platformer.Services;
import java.util.Hashtable;
import platformer.Entities.EntityConstants.PlatformConstants;
import platformer.Entities.EntityConstants.PlatformConstants.PresetPlatform;
import platformer.Entities.EntityConstants.WallConstants.PresetWall;
// Base Class //
public class ServicesConstants {
    public static Level[] levelArray = {
            new Level( // Level name, platforms, walls
                    "Tutorial",

                    new Hashtable<int[], PresetPlatform>() // Platforms //
                    {{
                            put(new int[] {-150, 500, 500, 12}, PlatformConstants.StandardPlatform);
                            put(new int[] {350, 470, 500, 12}, PlatformConstants.StandardPlatform);
                            put(new int[] {750, 500, 500, 12}, PlatformConstants.StandardPlatform);
                            put(new int[] {1050, 470, 100, 12}, PlatformConstants.StandardPlatform);
                            put(new int[] {1250, 500, 100, 12}, PlatformConstants.StandardPlatform);
                            put(new int[] {1400, 470, 100, 12}, PlatformConstants.StandardPlatform);
                            put(new int[] {1550, 500, 200, 12}, PlatformConstants.StandardPlatform);
                            put(new int[] {2145, 500, 200, 12}, PlatformConstants.StandardPlatform);
                    }},

                    new Hashtable<int[], PresetWall>() // Walls //
                    {{

                    }},

                    0, 5000, -1000, 1000
            )
    };
}
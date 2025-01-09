// File Directory //
package platformer.Services;

// Imports //
import platformer.Entities.Platform;
import platformer.Entities.Wall;

// Base Class //
public class Level {
    // Data //
    public String backgroundName;
    public int[][] platforms = {};
    public int[][] walls;

    // Constructors //
    public Level(String backgroundName, int[][] platforms, int[][] walls) {
        // Init Data //
        this.backgroundName = backgroundName;
        this.platforms = platforms;
        this.walls = walls;
    }

    // Base Methods //
    public void Clear() {
        Platform.platformList.clear();
        Wall.wallList.clear();
    }

    public void Load() {
        Clear();

        for (int[] platformData : platforms) {
            int xPos = platformData[0];
            int yPos = platformData[1];
            int width = platformData[2];
            int height = platformData[3];

            new Platform(xPos, yPos, width, height);
        }

        for (int[] wallData : walls) {
            int xPos = wallData[0];
            int yPos = wallData[1];
            int width = wallData[2];
            int height = wallData[3];

            new Wall(xPos, yPos, width, height);
        }
    }
}
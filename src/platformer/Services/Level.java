// File Directory //
package platformer.Services;

import javax.swing.ImageIcon;

import platformer.Main;
import platformer.Entities.EntityConstants.PlatformConstants.PresetPlatform;
// Imports //
import platformer.Entities.EntityConstants.WallConstants.PresetWall;
import platformer.Entities.Platform;
import platformer.Entities.Wall;
import platformer.Gui.CoreFrame;
import java.awt.Graphics;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;

// Base Class //
public class Level {
    // Static Data //
    public static CoreFrame frame = Main.coreFrame;
    public static Level currentLevel;
    // Data //
    public String backgroundName;
    public Dictionary<int[], PresetPlatform> platforms = new Hashtable<int[], PresetPlatform>();
    public Dictionary<int[], PresetWall> walls = new Hashtable<int[], PresetWall>();
    int B1, B2, B3, B4;

    public ImageIcon bgImage;

    // Constructors //
    public Level(String backgroundName, Hashtable<int[], PresetPlatform> platforms, Hashtable<int[], PresetWall> walls, int B1, int B2, int B3, int B4) {
        // Init Data //
        this.backgroundName = backgroundName;
        this.platforms = platforms;
        this.walls = walls;
        this.B1 = B1;
        this.B2 = B2;
        this.B3 = B3;
        this.B4 = B4;
    }

    // Static Methods //
    public static void Load(String backgroundName)
    {
        for (Level level : ServicesConstants.levelArray)
        {
            // Conditions //
            if (level.backgroundName != backgroundName)
                continue;
            // Settings //
            level.Load();
            level.bgImage = new ImageIcon("platformer/Gui/Images/" + backgroundName + ".png");
            // Reset //
            break;
        }
    }
    // Public Methods //
    public void Update(Graphics graphics)
    {
        graphics.drawImage(bgImage.getImage(), 0, 0, frame.getWidth(), frame.getHeight(), null);
    }
    
    public void Clear() {
        Platform.platformList.clear();
        Wall.wallList.clear();
        currentLevel = null;
    }
    // Private Methods //
    private void Load() {
        Clear();
        Main.setBoundaries(B1, B2, B3, B4);

        for (Enumeration<int[]> keys = platforms.keys(); keys.hasMoreElements(); keys.nextElement())
        {
            int[] platformData = keys.nextElement();
            int xPos = platformData[0];
            int yPos = platformData[1];
            int width = platformData[2];
            int height = platformData[3];

            Platform platform = new Platform(xPos, yPos, width, height);
            platform.applyPreset(platforms.get(platformData));
        }

        for (Enumeration<int[]> keys = walls.keys(); keys.hasMoreElements(); keys.nextElement())
        {
            int[] wallData = keys.nextElement();
            int xPos = wallData[0];
            int yPos = wallData[1];
            int width = wallData[2];
            int height = wallData[3];

            Wall wall = new Wall(xPos, yPos, width, height);
        }

        currentLevel = this;
    }
}
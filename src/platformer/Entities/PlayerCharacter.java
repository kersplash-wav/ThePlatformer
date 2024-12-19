// Folder Address //
package platformer.Entities;

// Imports //
import platformer.Constants.PlayerSettings;
import platformer.Tools.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.geom.Dimension2D;
import java.awt.geom.Line2D;
import java.awt.Graphics2D;

//import static platformer.Main.CoreFrames;
//import static platformer.Main.displays;

// Base Class //
public class PlayerCharacter extends Entity {
    // Data //
    private final Point movementAxis = new Point(0, 0);
    private Tool equippedTool;

    // Constructors //
    public PlayerCharacter() {
        super(new Point2D.Double(0, 0), new Dimension(PlayerSettings.width, PlayerSettings.height));
    }

    public void equipTool(Tool tool) {
        equippedTool = tool;
    }

    // Override Methods //
    @Override
    public void update() {
        // Data //
        Point2D velocity = this.getVelocity();
        // Physics //
        updatePhysics();
        // Movemement //
        velocity.setLocation(movementAxis.getX(), velocity.getY());
    }
}
// Folder Address //
package platformer.Entities;

// Imports //
import platformer.Constants.PlayerSettings;
import platformer.Tools.*;
import java.awt.geom.Point2D;

import java.awt.Dimension;
import java.awt.Point;

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

    //
    public boolean isGrounded() {
        // Character Data //
        Point2D underPlayerL = new Point2D.Double(hitBox.getMinX(), hitBox.getMaxY());
        Point2D underPlayerR = new Point2D.Double(hitBox.getMaxX(), hitBox.getMaxY());
        // Check through every platform //
        for (Entity entity : Platform.platformList) {
            // Conditions //
            if (entity == this)
                continue;
            // Checks //
            if (entity.hitBox.contains(underPlayerL)) {
                // Debug //
                /*
                 * System.out.println("Detecting!");
                 * setPosition(getPosition().getX(), platform.getPosition().getY() -
                 * playerSize.getHeight());
                 */
                // Experimental //
                translateVelocity(entity.getIntersectEscape(underPlayerL));
                // Success //
                return true;
            }

            if (entity.hitBox.contains(underPlayerR)) {
                translateVelocity(entity.getIntersectEscape(underPlayerR));
                // Success //
                return true;
            }
            // Fail //
        }
        return false;
    }

    // Override Methods //
    @Override
    public void updatePhysics() {
        super.updatePhysics();
        isGrounded();
    }

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
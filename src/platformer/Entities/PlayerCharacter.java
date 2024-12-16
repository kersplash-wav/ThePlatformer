// Folder Address //
package platformer.Entities;

// Imports //
import platformer.Constants.PlayerSettings;
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

    // Constructors //
    public PlayerCharacter() {
        super(new Point2D.Double(0, 0), new Dimension(PlayerSettings.width, PlayerSettings.height));
    }

    // Base Methods //
    public boolean isGrounded() {
        // Character Data //
        Dimension2D playerSize = getSize();
        Point2D underPlayer = new Point2D.Double(hitBox.getCenterX(), hitBox.getCenterY() + playerSize.getHeight() / 2);
        // Check through every platform //
        for (Platform platform : platformList) {
            // Conditions //
            if (platform.hitBox.contains(underPlayer)) {
                // Debug //
                /*
                 * System.out.println("Detecting!");
                 * setPosition(getPosition().getX(), platform.getPosition().getY() -
                 * playerSize.getHeight());
                 */
                // Experimental //
                Point2D bounce = platform.getIntersectEscape(underPlayer);
                System.out.println(bounce.getY());
                setVelocity(getVelocity().getX() + bounce.getX(), bounce.getY());
                // Success //
                return true;
            }
            // Fail //
        }
        return false;
    }

    // Override Methods //
    @Override
    public void update() {
        // Data //
        Point2D velocity = this.getVelocity();
        // Physics //
        updatePhysics();
        // Ground //
        if (isGrounded()) {
            System.out.println("GROUNDED!");
        }
        // Movemement //
        velocity.setLocation(movementAxis.getX(), velocity.getY());
    }
}
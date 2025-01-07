// Folder Address //
package platformer.Entities;

// Imports //
import platformer.Constants.PlayerSettings;
import platformer.Tools.*;
import java.awt.geom.Point2D;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

//import static platformer.Main.CoreFrames;
//import static platformer.Main.displays;

// Base Class //
public class PlayerCharacter extends Entity {
    // Data //
    private Point2D movementAxis = new Point2D.Double(0, 0);
    private Tool equippedTool;

    // Constructors //
    public PlayerCharacter() {
        super(new Point2D.Double(0, 0), new Dimension(PlayerSettings.width, PlayerSettings.height));
    }

    public void equipTool(Tool tool) {
        equippedTool = tool;
    }

    //
    public Entity isGrounded() {
        // Character Data //
        Point2D underPlayerL = new Point2D.Double(hitBox.getMinX(), hitBox.getMaxY());
        Point2D underPlayerR = new Point2D.Double(hitBox.getMaxX(), hitBox.getMaxY());
        // Check through every platform //
        for (Entity entity : entityList) {
            // Conditions //
            if (entity == this)
                continue;

            entity.setColour(Color.BLUE);
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
                entity.setColour(Color.MAGENTA);
                // Success //
                return entity;
            }

            if (entity.hitBox.contains(underPlayerR)) {
                translateVelocity(entity.getIntersectEscape(underPlayerR));
                entity.setColour(Color.MAGENTA);
                // Success //
                return entity;
            }
            // Fail //
        }
        return null;
    }

    // Override Methods //
    @Override
    public void updatePhysics() {
        super.updatePhysics();
        // setGravityEffect(isGrounded());
        // if (isGrounded()) {
        // translateVelocity()
        // }

    }

    @Override
    public void update() {
        // Data //
        Point2D velocity = this.getVelocity();
        // Physics //
        updatePhysics();

        // Movement //
        if (isGrounded() != null) {
            setGravityEffect(false);
            setVelocity(
                    new Point2D.Double(velocity.getX() + movementAxis.getX(), velocity.getY() + movementAxis.getY()));
        } else {
            setGravityEffect(true);
            setVelocity(new Point2D.Double(velocity.getX() + movementAxis.getX(), velocity.getY()));
        }
    }

    public void setMovementAxis(Point2D point) {
        movementAxis = point;
    }
}
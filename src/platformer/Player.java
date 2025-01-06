package platformer;

import platformer.Gui.*;
import platformer.Entities.*;
import platformer.Tools.*;
import java.awt.geom.Point2D;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import java.awt.Color;

public class Player {
    // Instance Data //
    Graphics2D graphics;
    PlayerCharacter character;
    CoreFrame coreFrame;
    Camera camera;
    Tool equipedTool;

    /**
     * Creates a player
     */
    public Player() {
        // Initialize Character //
        character = new PlayerCharacter();
        character.setGravityEffect(true);
        character.setColour(Color.GRAY);
        // Get Gui Renderer //
        graphics = (Graphics2D) camera.getGraphics();
    }

    public void render() {
        Point2D offset = camera.getPosition();
        for (Entity entity : Entity.entityList) {
            entity.render(graphics, offset);
        }
    }

    public boolean isGrounded() {
        // Character Data //
        Rectangle2D hitBox = character.hitBox;
        Point2D underPlayerL = new Point2D.Double(hitBox.getMinX(), hitBox.getMaxY());
        Point2D underPlayerR = new Point2D.Double(hitBox.getMaxX(), hitBox.getMaxY());
        // Check through every platform //
        for (Entity entity : Platform.platformList) {
            // Conditions //
            if (entity == character)
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
                Point2D bounce = entity.getIntersectEscape(underPlayerL);
                character.setVelocity(character.getVelocity().getX() + bounce.getX(), bounce.getY());
                // Success //
                return true;
            }

            if (entity.hitBox.contains(underPlayerR)) {
                Point2D bounce = entity.getIntersectEscape(underPlayerR);
                character.setVelocity(character.getVelocity().getX() + bounce.getX(), bounce.getY());
                // Success //
                return true;
            }
            // Fail //
        }
        return false;
    }

}
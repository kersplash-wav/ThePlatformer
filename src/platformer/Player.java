package platformer;

import platformer.Gui.*;
import platformer.Entities.*;
import platformer.Tools.*;
import java.awt.geom.Point2D;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import java.awt.Color;

public class Player {
    // Instance Data //
    Graphics graphics;
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
        character.setGravityEffect(false);
        character.setColour(Color.GRAY);
    }

    public void render() {
        graphics = camera.getGraphics();
        Point2D offset = camera.getPosition();
        for (Entity entity : Entity.entityList) {
            entity.render(graphics, offset);
        }
    }

    public Point2D getPosition() {
        return character.getPosition();
    }
}
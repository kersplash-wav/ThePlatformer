package platformer;

import platformer.Gui.*;
import platformer.Entities.*;
import platformer.Tools.*;
import java.awt.GraphicsDevice;
import java.awt.geom.Point2D;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.awt.geom.Rectangle2D;

import java.awt.Color;

public class Player {
    // Static Data //
    public static final ArrayList<Player> playerlist = new ArrayList<>();
    // Instance Data //
    Graphics2D graphics;

    PlayerCharacter character;
    Camera camera;

    GraphicsDevice viewDisplay;
    Point2D viewLocation;
    Dimension viewSize;

    int playerID;
    Tool equipedTool;

    // Constructor //
    private Player() {
        this.playerID = playerlist.size();
        playerlist.add(this);
    }

    /**
     * Creates a player
     * 
     * @param playerID     identifying number of the player
     * @param viewDisplay  the display device of this players view
     * @param viewLocation the location this players view
     * @param viewSize     the size of this players view
     */
    public Player(GraphicsDevice viewDisplay, Point2D viewLocation, Dimension viewSize) {
        // Constructor Chaining //
        this();
        // Initialize Data //
        this.viewDisplay = viewDisplay;
        this.viewLocation = viewLocation;
        this.viewSize = viewSize;
        // Initialize Character //
        character = new PlayerCharacter();
        character.setGravityEffect(true);
        character.setColour(Color.GRAY);
        // Initialize Camera //
        camera = new Camera(CoreFrame.getCoreFrame(viewDisplay), viewSize);
        // Get Gui Renderer //
        graphics = (Graphics2D) camera.getGraphics();
    }

    public static Player getPlayer(int playerID) {
        return playerlist.get(playerID);
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
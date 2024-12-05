package platformer.Entities;

import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.geom.Point2D;

import platformer.Main;
import platformer.Gui.CoreFrame;

public class Camera 
{
    // Character //
    public final Entity character;
    // Coordinates //
    public Point2D position = new Point2D.Double();
    public Point2D offset = new Point2D.Double();
    // Graphics Devices //
    public final GraphicsDevice display;
    // Constructor //
    /**
     * Creates a camera object which follows a player and renders objects relative to the player.
     * @param display the display tied to this camera
     * @param character the character this camera follows
     */
    public Camera(GraphicsDevice display, Player character) 
    {
        // Initialize Settings //
        this.display = display;
        this.character = character;
        // Add Reference //
        Main.Cameras.put(display, this);
    }
    // Base Methods //
    public void update()
    {
        // Character Data //
        Point2D characterPos = character.getPosition();
        double xPos = characterPos.getX();
        double yPos = characterPos.getY();
        // Settings //
        position.setLocation(xPos + offset.getX(), yPos + offset.getY());
        // Display //
        render();
    }

    /**
     * Renders all objects based on this cameras position.
     */
    public void render()
    {
        // Core Frame //
        CoreFrame coreFrame = Main.CoreFrames.get(display);
        // Gui //
        Graphics2D graphics = (Graphics2D)coreFrame.getGraphics();
        // Render Everything Else //
        for (var entity : Entity.entityList)
        {
            // Success //
            entity.render(graphics, position);
        }
    }
}
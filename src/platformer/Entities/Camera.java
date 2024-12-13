package platformer.Entities;

import static platformer.Main.CoreFrames;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.geom.Point2D;
import java.awt.geom.Dimension2D;

import platformer.Main;
import platformer.Gui.CoreFrame;

public class Camera 
{
    // Character //
    public final Entity character;
    // Coordinates //
    public Point2D position = new Point2D.Double();
    public Point2D offset = new Point2D.Double();
    // Screen Data //
    public final Dimension screenSize;
    // Graphics Devices //
    public final GraphicsDevice display;
    // Constructor //
    /**
     * Creates a camera object which follows a player and renders objects relative to the player.
     * @param display the display tied to this camera
     * @param character the character this camera follows
     */
    public Camera(GraphicsDevice display, PlayerCharacter character) 
    {
        // Frames //
        CoreFrame coreFrame = CoreFrames.get(display);
        // Initialize Settings //
        this.display = display;
        this.character = character;
        // Frame Data //
        screenSize = coreFrame.getSize();
        // Add Reference //
        Main.Cameras.put(display, this);
    }
    
    // Base Methods //
    public void update()
    {
        // Character Data //
        Point2D characterPos = character.getPosition();
        double xPos = characterPos.getX() - screenSize.getWidth() / 2 + offset.getX();
        double yPos = characterPos.getY() - screenSize.getHeight() / 2 + offset.getY();
        // Settings //
        position.setLocation(xPos, yPos);
    }

    public Point2D toGlobal(Point2D localPoint)
    {
        // Return //
        return new Point2D.Double(localPoint.getX() + position.getX(), localPoint.getY() + position.getY());
    }

    public Point2D toLocal(Point2D globalPoint)
    {
        return new Point2D.Double(globalPoint.getX()-position.getX(), globalPoint.getY()-position.getY());
    }
    /**
     * Renders all objects based on this cameras position.
     */
    public void render(Graphics2D graphics)
    {
        update();
        // Data //
        double xPos = position.getX() - screenSize.getWidth() / 2 + offset.getX();
        double yPos = position.getY() - screenSize.getHeight() / 2 + offset.getY();
        // Render Everything Else //
        //Entity.renderAll(graphics);
        Entity.renderAll(graphics, this);
    }
}
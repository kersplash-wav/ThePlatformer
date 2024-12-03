package platformer.Tools;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import platformer.Entities.Platform;

public class Tool
{
    // Data //
    private long deltaTime;
    // Constructors //
    public Tool()
    {
        
    }
    // Update Methods //
    public final void update()
    {
        // Data //
        long currentTime = System.currentTimeMillis();
        // Activate //
        update(currentTime - deltaTime);
        // Reset //
        deltaTime = currentTime;
    }
    
    private void update(long deltaTime) {}
}

class Raycast
{
    // Data //
    private final Point2D origin;
    private final Point2D direction;
    private final double length;
    // Constructors //
    /**
     * @param origin
     * @param direction
     */
    public Raycast(Point2D origin, Point2D direction, double length)
    {
        this.origin = origin;
        this.direction = direction;
        this.length = length;

        setMagnitude(length);
    }
    // Math Methods //
    private double getMagnitude()
    {
        return direction.distance(0, 0);
    }

    private void setMagnitude(double magnitude)
    {
        double currentMagnitude = getMagnitude();
        double sizeFactor = magnitude/currentMagnitude;
        direction.setLocation(direction.getX()*sizeFactor, direction.getY()*sizeFactor);
    }
    // Base Methods //
    private Line2D getLine()
    {
        Point2D destination = new Point2D.Double(origin.getX() + direction.getX(), origin.getY() + direction.getY());
        return new Line2D.Double(origin.getX(), origin.getY(), destination.getX(), destination.getY());
    }
    // Action Methods //
    public boolean cast()
    {
        for (Platform platform : Platform.platformList)
        {
            // Data //
            Rectangle2D hitBox = platform.hitBox;
            // Checks //
            if (hitBox.intersectsLine(getLine()))
                return true;
        }
        // Fail //
        return false;
    }
    // Visual Methods //
    private void display(Graphics2D graphics)
    {
        graphics.draw(getLine());
    }
}
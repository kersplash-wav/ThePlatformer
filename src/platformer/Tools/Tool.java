package platformer.Tools;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import platformer.Entities.Platform;
import platformer.Entities.Player;

public class Tool implements MouseListener
{
    // Character Data //
    Player holder;
    // Data //
    private long deltaTime;
    private Raycast rayCast;
    // Constructors //
    public Tool(Player holder)
    {
        this.holder = holder;
        holder.equipTool(this);
        rayCast = new Raycast(holder.getPosition(), holder.getPosition(), deltaTime);
    }
    // Update Methods //
    public final void update(Graphics2D graphics)
    {
        System.out.println("Updating!");
        PointerInfo mouseData = getMouse();
        // Data //
        long currentTime = System.currentTimeMillis();
        // Activate //
        update(currentTime - deltaTime, graphics);
        rayCast.origin = new Point2D.Double(holder.hitBox.getCenterX(), holder.hitBox.getCenterY());
        rayCast.lookAt(mouseData.getLocation());
        // Reset //
        deltaTime = currentTime;
    }
    
    // Private Methods //
    private void update(long deltaTime, Graphics2D graphics) 
    {
        System.out.println("Displaying!");
        rayCast.display(graphics);
    }

    private PointerInfo getMouse()
    {
        return MouseInfo.getPointerInfo();
    }
    // Input //
    @Override
    public void mouseClicked(MouseEvent e) {

    }
    @Override
    public void mousePressed(MouseEvent e) {

    }
    @Override
    public void mouseReleased(MouseEvent e) {

    }
    @Override
    public void mouseEntered(MouseEvent e) {

    }
    @Override
    public void mouseExited(MouseEvent e) {

    }
}

class Raycast
{
    // Data //
    public Point2D origin;
    public Point2D direction;
    public double length;
    // Constructors //
    /**
     * @param origin
     * @param direction
     */
    public Raycast(Point2D origin, Point2D direction, double length)
    {
        // Initialize Settings //
        this.origin = origin;
        this.direction = direction;
        this.length = length;
        // Initialize Raycast Length //
        setMagnitude(length);
    }
    // Math Methods //
    private double getMagnitude()
    {
        return direction.distance(0, 0);
    }

    private void setMagnitude(double magnitude)
    {
        // Magnitude Data //
        double currentMagnitude = getMagnitude();
        // Calculations //
        double sizeFactor = magnitude/currentMagnitude;
        // Settings //
        direction.setLocation(direction.getX()*sizeFactor, direction.getY()*sizeFactor);
        // Debug //
        System.out.println(currentMagnitude);
    }
    // Base Methods //
    private Line2D getLine()
    {
        Point2D destination = new Point2D.Double(origin.getX() + direction.getX(), origin.getY() + direction.getY());
        return new Line2D.Double(origin.getX(), origin.getY(), destination.getX(), destination.getY());
    }

    public Point2D lookAt(Point2D destination)
    {
        // Get Offset //
        Point2D offset = new Point2D.Double();
        offset.setLocation(destination.getX() - origin.getX(), destination.getY() - origin.getY());
        // Settings //
        direction = offset;
        // Set Magnitude //
        setMagnitude(offset.distance(0, 0));
        // Return Copy //
        return (Point2D)direction.clone();
    }
    // Action Methods //
    public boolean cast()
    {
        for (Platform platform : Platform.platformList)
        {
            // Hitbox //
            Rectangle2D hitBox = platform.hitBox;
            // Checks //
            if (hitBox.intersectsLine(getLine()))
                return true;
        }
        // Fail //
        return false;
    }
    // Visual Methods //
    public void display(Graphics2D graphics)
    {
        System.out.println("Displaying");
        boolean success = cast();
        graphics.setColor(success ? Color.green : Color.red);
        graphics.draw(getLine());
    }
}
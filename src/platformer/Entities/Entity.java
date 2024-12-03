// Folder Address //
package platformer.Entities;
import platformer.Gui.CoreFrame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.geom.Dimension2D;
// Geometry Imports //
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import platformer.Main;

// Graphics Imports //
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;

// Universal Imports //
import static platformer.Constants.WorldSettings.*;
// Base Class //
public class Entity extends Object
{
    // Data //
    public final Rectangle2D hitBox;
    private Point2D velocity;
    private boolean affectedByGravity = false;
    private boolean stayInScreen = false;
    private Color colour = Color.red;
    // Arrays //
    public static final ArrayList<Entity> entityList = new ArrayList<Entity>();
    // Constructor //
    protected Entity()
    {
        hitBox = new Rectangle2D.Double();
    }

    public Entity(Point2D position, Dimension2D size)
    {

        this();
        // Extract Data //
        double xPos = position.getX();
        double yPos = position.getY();
        double width = size.getWidth();
        double height = size.getHeight();
        // Initialize //
        hitBox.setRect(xPos, yPos, width, height);
        velocity = new Point2D.Double(0,0);
        // Add Reference //
        System.out.println("Added to list!");
        entityList.add(this);
        
        for (var entity : entityList)
            System.out.println(entity);
    }
    
    public Entity(int xPos, int yPos, int width, int height)
    {
        // Activate Previous Constructor //
        this(new Point(xPos, yPos), new Dimension(width, height));
    }
    // Private Methods //
    private boolean checkBounds(GraphicsDevice display)
    {
        // Conditions //
        if (!stayInScreen)
            return false;
        // Gui //
        CoreFrame coreFrame = Main.CoreFrames.get(display);
        // Data //
        Dimension size = coreFrame.getSize();
        Point2D position = getPosition();
        // Extract Data //
        double xPos = position.getX();
        double yPos = position.getY();
        // Calculations //
        double newXPos = Math.clamp(xPos, 0, size.width);
        double newYPos = Math.clamp(yPos, 0, size.height);
        // Checks //
        if (newXPos != xPos || newYPos != yPos)
        { // Success //
            setPosition(newXPos, newYPos);
            return true;
        }
        // Fail //
        return false;
    }

    private void updateGravity()
    {
        // Conditions //
        if (!affectedByGravity)
            return;
        // Settings //
        velocity.setLocation(velocity.getX() + worldGravity.getX(), velocity.getY() + worldGravity.getY());
        System.out.println(velocity);
    }
    // Base Methods //
    public Point2D getPosition() 
    {
        double xPos = hitBox.getX();
        double yPos = hitBox.getY();

        return new Point2D.Double(xPos, yPos);
    }

    public void setPosition(double x, double y) 
    {
        hitBox.setRect(x, y, hitBox.getWidth(), hitBox.getHeight());
    }

    public void setPosition(Point2D point) 
    {
        hitBox.setRect(point.getX(), point.getY(), hitBox.getWidth(), hitBox.getHeight());
    }

    public Point2D getVelocity()
    {
        return this.velocity;
    }

    public void setVelocity(double x, double y) 
    {
        this.velocity.setLocation(x, y);
    }

    public void setVelocity(Point2D point) 
    {
        this.velocity.setLocation(point);
    }

    public Point2D translate(double x, double y)
    {
        this.setPosition(getPosition().getX() + x, getPosition().getY() + y);

        return getPosition();
    }

    public Point2D translate(Point2D point)
    {
        this.setPosition(getPosition().getX() + point.getX(), getPosition().getY() + point.getY());
        return getPosition();
    }

    public Dimension2D getSize()
    {
        return hitBox.getBounds2D()
            .getBounds()
            .getSize();
    }

    public void setSize(Dimension2D size)
    {
        Point2D position = getPosition();
        hitBox.setFrameFromCenter(position.getX(), position.getY(), size.getWidth(), size.getHeight());
    }

    public void setSize(double width, double height)
    {
        Point2D position = getPosition();
        hitBox.setFrameFromCenter(position.getX(), position.getY(), width, height);
    }

    public void setGravityEffect(boolean isAffected)
    {
        affectedByGravity = isAffected;
    }

    public void enableScreenBounds(boolean isAffected){
        stayInScreen = isAffected;
    }

    public void update() {}
    // Colour Methods //
    public void setColour(Color colour){
        this.colour = colour;
    }

    public Color getColour(){
        return this.colour;
    }
    // Update Methods //
    public void updatePhysics(GraphicsDevice display)
    {
        updateGravity();
        checkBounds(display);
        translate(velocity);
    }

    public void updatePhysics()
    {
        updateGravity();
        translate(velocity);
    }

    public void render(Graphics2D graphics)
    {
        update();
        // Data Source //
        Point2D position = this.getPosition();
        Dimension2D size = this.getSize();
        // Extract Data //
        int xPos = (int)position.getX();
        int yPos = (int)position.getY();

        int width = (int)size.getWidth();
        int height = (int)size.getHeight();
        // Settings //
        graphics.setColor(getColour());
        graphics.fillRect(xPos + width / 2, yPos + height / 2, width, height);
        graphics.setColor(Color.black);
        graphics.drawRect(xPos + width / 2, yPos + height / 2, width, height);
    }
    
    public void render(Graphics2D graphics, Point2D position)
    {
        update();
        position.setLocation(position.getX() - this.hitBox.getX(), position.getY() - this.hitBox.getY());
        Dimension2D size = this.getSize();
        // Extract Data //
        int xPos = (int)position.getX();
        int yPos = (int)position.getY();

        int width = (int)size.getWidth();
        int height = (int)size.getHeight();
        // Settings //
        graphics.setColor(getColour());
        graphics.fillRect(xPos + width / 2, yPos + height / 2, width, height);
        graphics.setColor(Color.black);
        graphics.drawRect(xPos + width / 2, yPos + height / 2, width, height);
    }

    public static void renderAll(Graphics2D graphics)
    {
        for (Entity entity : entityList)
        {
            System.out.println("RENDERING!");
            entity.render(graphics);
        }
    }
}
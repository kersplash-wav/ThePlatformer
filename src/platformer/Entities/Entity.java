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
public abstract class Entity extends Object
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

    /**
     * Creates a general entity object with a hitbox.
     * @param position the position of the centre of this entity
     * @param size the width and height of this entity
     */
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
    
    /**
     * Creates a general entity object with a hitbox.
     * @param xPos the x position of the centre of this entity
     * @param yPos the y position of the centre of this entity
     * @param width the width of this entity
     * @param height the height of this entity
     */
    public Entity(int xPos, int yPos, int width, int height)
    {
        // Activate Previous Constructor //
        this(new Point(xPos, yPos), new Dimension(width, height));
    }
    // Private Methods //

    /**
     * If this object is affected by gravity, update its velocity.
     */
    private void updateGravity()
    {
        // Conditions //
        if (!affectedByGravity)
            return;
        // Settings //
        velocity.setLocation(velocity.getX() + worldGravity.getX(), velocity.getY() + worldGravity.getY());
    }
    // Base Methods //
    /**
     * Get the position of the centre of this entity.
     * @return the position of the centre of this entity
     */
    public Point2D getPosition() 
    {
        double xPos = hitBox.getX();
        double yPos = hitBox.getY();

        return new Point2D.Double(xPos, yPos);
    }

    /**
     * Set the position of the centre of this entity.
     * @param x the x position of the centre of this entity
     * @param y the y position of the centre of this entity
     */
    public void setPosition(double x, double y) 
    {
        hitBox.setRect(x, y, hitBox.getWidth(), hitBox.getHeight());
    }

    /**
     * Set the position of the centre of this entity.
     * @param point the position of the centre of this entity
     */
    public void setPosition(Point2D point) 
    {
        hitBox.setRect(point.getX(), point.getY(), hitBox.getWidth(), hitBox.getHeight());
    }

    /**
     * Get the velocity of this entity.
     * @return the velocity of this entity
     */
    public Point2D getVelocity()
    {
        return this.velocity;
    }

    /**
     * Set the velocity of this entity.
     * @param x the desired velocities x component
     * @param y the desired velocities y component
     */
    public void setVelocity(double x, double y) 
    {
        this.velocity.setLocation(x, y);
    }

    /**
     * Set the velocity of this entity.
     * @param point the desired velocity
     */
    public void setVelocity(Point2D point) 
    {
        this.velocity.setLocation(point);
    }

    /**
     * Translate this entity.
     * @param x the desired translations x component
     * @param y the desired translations y component
     * @return the new position of this entity after translation
     */
    public Point2D translate(double x, double y)
    {
        this.setPosition(getPosition().getX() + x, getPosition().getY() + y);

        return getPosition();
    }

    /**
     * Translate this entity.
     * @param point the desired translation
     * @return the new position of this entity after translation
     */
    public Point2D translate(Point2D point)
    {
        this.setPosition(getPosition().getX() + point.getX(), getPosition().getY() + point.getY());
        return getPosition();
    }

    /**
     * Get the size of this entity.
     * @return the width and height of this entity
     */
    public Dimension2D getSize()
    {
        return hitBox.getBounds2D()
            .getBounds()
            .getSize();
    }

    /**
     * Set the size of this entity.
     * @param size the desired size of this entity
     */
    public void setSize(Dimension2D size)
    {
        Point2D position = getPosition();
        hitBox.setFrameFromCenter(position.getX(), position.getY(), size.getWidth(), size.getHeight());
    }

    /**
     * Set the size of this entity.
     * @param width the desired width of this entity
     * @param height the desired height of this entity
     */
    public void setSize(double width, double height)
    {
        Point2D position = getPosition();
        hitBox.setFrameFromCenter(position.getX(), position.getY(), width, height);
    }

    /**
     * Set whether this entity is affected by gravity.
     * @param isAffected whether this entity is affected by gravity
     */
    public void setGravityEffect(boolean isAffected)
    {
        affectedByGravity = isAffected;
    }
    
    /**
     * Abstract method that is automatically run periodically.
     * You must override this function.
     */
    abstract public void update();

    // Colour Methods //
    /**
     * Set the colour of this entities hitbox.
     * @param colour the desired colour
     */
    public void setColour(Color colour){
        this.colour = colour;
    }

    /**
     * Get the colour of this entities hitbox.
     * @return the colour of this entities hitbox
     */
    public Color getColour(){
        return this.colour;
    }
    // Update Methods //
    /**
     * Update this entities physics: gravity, collisions, and velocity. 
     */
    public void updatePhysics()
    {
        updateGravity();
        translate(velocity);
    }

    /**
     * Render this object.
     * @param graphics the window to render this object to
     */
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
        graphics.fillRect(xPos, yPos, width, height);
        graphics.setColor(Color.black);
        graphics.drawRect(xPos, yPos, width, height);
    }
    
    /**
     * Render this object.
     * @param graphics the window to render this object to
     * @param position the position of the camera tied to this window
     */
    public void render(Graphics2D graphics, Point2D position)
    {
        update();
        Point2D thisPosition = this.getPosition();
        Dimension2D size = this.getSize();
        // Extract Data //
        int xPos = (int)(thisPosition.getX() - position.getX());
        int yPos = (int)(thisPosition.getY() - position.getY());

        int width = (int)size.getWidth();
        int height = (int)size.getHeight();
        // Settings //
        graphics.setColor(getColour());
        graphics.fillRect(xPos - width / 2, yPos + height / 2, width, height);
        graphics.setColor(Color.black);
        graphics.drawRect(xPos - width / 2, yPos + height / 2, width, height);
    }

    /**
     * Render all entities to this window
     * @param graphics the window to render all entites to
     */
    public static void renderAll(Graphics2D graphics)
    {
        for (Entity entity : entityList)
        {
            entity.render(graphics);
        }
    }

    public static void renderAll(Graphics2D graphics, Point2D position)
    {
        for (Entity entity : entityList)
        {
            entity.render(graphics, position);
        }
    }
}
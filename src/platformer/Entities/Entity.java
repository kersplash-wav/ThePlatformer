package platformer.Entities;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Graphics2D;

public abstract class Entity extends Object {

    /**
     * A list of all the entities, E.g: Platforms, characters, walls
     */
    public static final ArrayList<Entity> entityList = new ArrayList<Entity>();
    /**
     * The hitbox, contains the position and size of the entity
     */
    public final Rectangle2D hitBox;
    /**
     * The speed at which the entity is moving
     */
    private Point2D velocity;
    /**
     * If the entity is affected by gravity
     */
    private boolean affectedByGravity = false;
    /**
     * The colour of the entity
     */
    private Color colour = Color.black;

    /**
     * Main constructor, used for constructor chaining
     */
    protected Entity() {
        hitBox = new Rectangle2D.Double();
    }

    /**
     * Creates a general entity object with a hitbox.
     * 
     * @param position the position of the centre of this entity
     * @param size     the width and height of this entity
     */
    public Entity(Point2D position, Dimension2D size) {

        this();
        double xPos = position.getX();
        double yPos = position.getY();
        double width = size.getWidth();
        double height = size.getHeight();
        hitBox.setRect(xPos, yPos, width, height);
        velocity = new Point2D.Double(0, 0);
        entityList.add(this);
    }

    /**
     * Creates a general entity object with a hitbox.
     * 
     * @param xPos   the x position of the centre of this entity
     * @param yPos   the y position of the centre of this entity
     * @param width  the width of this entity
     * @param height the height of this entity
     */
    public Entity(int xPos, int yPos, int width, int height) {
        this(new Point(xPos, yPos), new Dimension(width, height));
    }

    /**
     * Set whether this entity is affected by gravity
     * 
     * @param isAffected whether this entity is affected
     */
    public void setGravityEffect(boolean isAffected) {
        affectedByGravity = isAffected;
    }

    /**
     * @return whether this entity is affected by gravity
     */
    public boolean getGravityEffect() {
        return affectedByGravity;
    }

    /**
     * Set the colour of this entity
     * 
     * @param colour the colour of the entity
     */
    public void setColour(Color colour) {
        this.colour = colour;
    }

    /**
     * @return the colour of the entity
     */
    public Color getColour() {
        return this.colour;
    }

    /**
     * Set the position of this entity
     * 
     * @param x the x position
     * @param y the y position
     */
    public void setPosition(double x, double y) {
        hitBox.setRect(x, y, hitBox.getWidth(), hitBox.getHeight());
    }

    /**
     * Set the position of this entity
     * 
     * @param point the position
     */
    public void setPosition(Point2D point) {
        hitBox.setRect(point.getX(), point.getY(), hitBox.getWidth(), hitBox.getHeight());
    }

    /**
     * Get the position of this entity
     * 
     * @return the position of the entity
     */
    public Point2D getPosition() {
        double xPos = hitBox.getX();
        double yPos = hitBox.getY();
        return new Point2D.Double(xPos, yPos);
    }

    /**
     * Set the velocity of this entity
     * 
     * @param x the x velocity
     * @param y the y velocity
     */
    public void setVelocity(double x, double y) {
        velocity.setLocation(x, y);
    }

    /**
     * Set the velocity of this entity
     * 
     * @param point the velocity
     */
    public void setVelocity(Point2D point) {
        velocity.setLocation(point);
    }

    /**
     * Move the velocity of this entity
     * Useful for applying gravity or friction.
     * 
     * @param point the translation
     */
    public void translateVelocity(Point2D point) {
        velocity.setLocation(velocity.getX() + point.getX(), velocity.getY() + point.getY());
    }

    /**
     * Get the velocity of this entity
     * 
     * @return the velocity
     */
    public Point2D getVelocity() {
        return velocity;
    }

    /**
     * Set the size of this entity
     * 
     * @param size the size
     */
    public void setSize(Dimension2D size) {
        Point2D position = getPosition();
        hitBox.setRect(position.getX(), position.getY(), size.getWidth(), size.getHeight());
    }

    /**
     * Set the size of this entity
     * 
     * @param width  the width
     * @param height the height
     */
    public void setSize(double width, double height) {
        Point2D position = getPosition();
        hitBox.setRect(position.getX(), position.getY(), width, height);
    }

    /**
     * Get the size of this entity
     * 
     * @return the size
     */
    public Dimension2D getSize() {
        return hitBox.getBounds2D()
                .getBounds()
                .getSize();
    }

    /**
     * Whether this entities hitbox contains this point
     * 
     * @param point the point to test
     * @return whether this entity contains the point
     */
    public boolean isIntersecting(Point2D point) {
        return this.hitBox.contains(point);
    }

    /**
     * Translate the entities position
     * 
     * @param x the x translation
     * @param y the y translation
     */
    public void translate(double x, double y) {
        this.setPosition(getPosition().getX() + x, getPosition().getY() + y);
    }

    /**
     * Translate this entities position
     * 
     * @param point the translation
     */
    public void translate(Point2D point) {
        this.setPosition(getPosition().getX() + point.getX(), getPosition().getY() + point.getY());
    }

    /**
     * Apply gravity to the entity
     */
    protected void applyGravity() {
        if (affectedByGravity)
            velocity.setLocation(velocity.getX(), velocity.getY() + EntityConstants.WorldConstants.worldGravity);
        else if (velocity.getY() > 0)
            velocity.setLocation(velocity.getX(), 0);
    }

    /**
     * Apply velocity to this entity
     */
    protected void applyVelocity() {
        translate(velocity);
    }

    /**
     * Run all updates on this entity
     * Runs gravity and velocity
     */
    public void update() {
        applyGravity();
        applyVelocity();
    }

    /**
     * Renders this entity to the screen
     * 
     * @param graphics the graphics to render this entity to
     */
    public void render(Graphics graphics) {
        render(graphics, new Point2D.Double());
    }

    /**
     * Renders this entity to the screen
     * 
     * @param _graphics the graphics to render this entity to
     * @param offset    the camera offset to apply while rendering
     */
    public void render(Graphics _graphics, Point2D offset) {

        Graphics2D graphics = (Graphics2D) _graphics;

        Point2D position = this.getPosition();
        Dimension2D size = this.getSize();

        int xPos = (int) position.getX() + (int) offset.getX();
        int yPos = (int) position.getY() + (int) offset.getY();

        int width = (int) size.getWidth();
        int height = (int) size.getHeight();

        graphics.setColor(getColour());
        graphics.fillRect(xPos, yPos, width, height);
        graphics.setColor(Color.black);
        graphics.setStroke(new BasicStroke(5));
        graphics.drawRect(xPos, yPos, width, height);
    }

    /**
     * Renders all entities to the screen
     * 
     * @param graphics the graphics to render them to
     * @param offset   the camera offset to apply when rendering
     */
    public static void renderAll(Graphics graphics, Point2D offset) {
        for (Entity entity : entityList) {
            entity.render(graphics, offset);
        }
    }

    /**
     * Update all entities
     */
    public static void updateAll() {
        for (Entity entity : entityList) {
            entity.update();
        }
    }
}
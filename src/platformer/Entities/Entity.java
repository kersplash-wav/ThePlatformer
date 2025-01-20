package platformer.Entities;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfigTemplate;

public abstract class Entity extends Object {

    public static final ArrayList<Entity> entityList = new ArrayList<Entity>();

    public final Rectangle2D hitBox;
    private Point2D velocity;
    private boolean affectedByGravity = false;
    private Color colour = Color.black;
    private boolean collisionsEnabled = true;
    private boolean visible = true;

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

    public void setGravityEffect(boolean isAffected) {
        affectedByGravity = isAffected;
    }

    public boolean getGravityEffect(boolean isAffected) {
        return affectedByGravity;
    }

    public void setCollisionsEnabled(boolean enabled) {
        collisionsEnabled = enabled;
    }

    public boolean getCollisionsEnabled() {
        return collisionsEnabled;
    }

    public void setColour(Color colour) {
        this.colour = colour;
    }

    public Color getColour() {
        return this.colour;
    }

    public void setVisibility(boolean visible){
        this.visible = visible;
    }

    public boolean getVisibility(){
        return visible;
    }

    public void setPosition(double x, double y) {
        hitBox.setRect(x, y, hitBox.getWidth(), hitBox.getHeight());
    }

    public void setPosition(Point2D point) {
        hitBox.setRect(point.getX(), point.getY(), hitBox.getWidth(), hitBox.getHeight());
    }

    public Point2D getPosition() {
        double xPos = hitBox.getX();
        double yPos = hitBox.getY();
        return new Point2D.Double(xPos, yPos);
    }

    public void setVelocity(double x, double y) {
        velocity.setLocation(x, y);
    }

    public void setVelocity(Point2D point) {
        velocity.setLocation(point);
    }

    public void translateVelocity(Point2D point) {
        velocity.setLocation(velocity.getX() + point.getX(), velocity.getY() + point.getY());
    }

    public Point2D getVelocity() {
        return velocity;
    }

    public void setSize(Dimension2D size) {
        Point2D position = getPosition();
        hitBox.setRect(position.getX(), position.getY(), size.getWidth(), size.getHeight());
    }

    public void setSize(double width, double height) {
        Point2D position = getPosition();
        hitBox.setRect(position.getX(), position.getY(), width, height);
    }

    public Dimension2D getSize() {
        return hitBox.getBounds2D()
                .getBounds()
                .getSize();
    }

    public boolean isIntersecting(Point2D point) {
        return this.hitBox.contains(point);
    }

    public void translate(double x, double y) {
        this.setPosition(getPosition().getX() + x, getPosition().getY() + y);
    }

    public void translate(Point2D point) {
        this.setPosition(getPosition().getX() + point.getX(), getPosition().getY() + point.getY());
    }

    protected void applyGravity() {
        if (affectedByGravity)
            velocity.setLocation(velocity.getX(), velocity.getY() + EntityConstants.WorldConstants.worldGravity);
        else if (velocity.getY() > 0)
            velocity.setLocation(velocity.getX(), 0);
    }

    protected void applyVelocity() {
        translate(velocity);
    }

    public void update() {
        applyGravity();
        applyVelocity();
    }

    public void render(Graphics graphics) {
        render(graphics, new Point2D.Double());
    }

    public void render(Graphics _graphics, Point2D offset) {
        if(!visible)
            return;

        Graphics2D graphics = (Graphics2D)_graphics;

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

    public static void renderAll(Graphics graphics, Point2D offset) {
        for (Entity entity : entityList) {
            entity.render(graphics, offset);
        }
    }

    public static void updateAll() {
        for (Entity entity : entityList) {
            entity.update();
        }
    }
}
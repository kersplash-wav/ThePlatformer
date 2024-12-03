package platformer.Entities;

// Imports //
import java.awt.geom.Point2D;
import java.awt.geom.Dimension2D;
import java.util.ArrayList;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Color;
import java.awt.Dimension;

// Class //
public class Platform extends Entity {
    // Public Data //
    public static final ArrayList<Platform> platformList = new ArrayList<Platform>();
    // Private Data //
    private double friction = 1;
    private double bounce = 0;

    // Constructor //
    public Platform(Point2D position, Dimension2D size) {
        // Activate Parent Constructor //
        super(position, size);
        // Add Reference //
        platformList.add(this);
    }

    public Platform(int xPos, int yPos, int width, int height) {
        // Activate Parent Constructor //
        this(new Point(xPos, yPos), new Dimension(width, height));
    }

    // Friction Methods //
    public void setFriction(double friction) {
        this.friction = friction;
    }

    public double getFriction() {
        return friction;
    }

    // Bounce Methods //
    public void setBounce(double bounce) {
        this.bounce = bounce;
    }

    public double getBounce() {
        return bounce;
    }

    /**
     * @param entity entity to test
     * @return whether the entity is intersecting this platform
     */
    public boolean isIntersecting(Entity entity) {
        return this.hitBox.intersects(entity.hitBox);
    }

    /**
     * @param entity entity to test
     * @return which direction the entity must travel to escape the bounds of this platform
     */
    public Point2D getIntersectEscape(Entity entity) {

        if(!isIntersecting(entity))
            return new Point2D.Double(0,0);

        double xOffset = 0;
        double yOffset = 0;

        if (this.hitBox.getMinX() < entity.hitBox.getMaxX())
            xOffset = -1;
        if (this.hitBox.getMaxX() > entity.hitBox.getMinX())
            xOffset = 1;
        if (this.hitBox.getMinY() < entity.hitBox.getMaxY())
            yOffset = -1;
        if (this.hitBox.getMaxY() > entity.hitBox.getMinY())
            yOffset = 1;

        return new Point2D.Double(xOffset, yOffset);
    }
}
package platformer.Entities;

// Imports //
import java.awt.geom.Point2D;
import java.awt.geom.Dimension2D;
import java.util.ArrayList;
import java.awt.Point;
import java.awt.Dimension;
import platformer.Constants;

// Class //
public class Platform extends Entity {
    // Public Data //
    public static final ArrayList<Platform> platformList = new ArrayList<Platform>();
    // Private Data //
    private double friction = Constants.Platform.StandardPlatform.friction;
    private double bounce = Constants.Platform.StandardPlatform.bounce;
    // Constructor //

    /**
     * Creates a platform object.
     * 
     * @param position the position of the centre of the platform
     * @param size     the width and height of this platform
     */
    public Platform(Point2D position, Dimension2D size) {
        // Activate Parent Constructor //
        super(position, size);
        // Add Reference //
        platformList.add(this);
    }

    /**
     * Creates a platform object.
     * 
     * @param xPos   the x position of the centre of this platform
     * @param yPos   the y position of the centre of this platform
     * @param width  the width of this platform
     * @param height the height of this platform
     */
    public Platform(int xPos, int yPos, int width, int height) {
        // Activate Parent Constructor //
        this(new Point(xPos, yPos), new Dimension(width, height));
    }

    // Friction Methods //

    /**
     * Sets the friction of this platform.
     * 
     * @param friction the desired friction of this platform
     */
    public void setFriction(double friction) {
        this.friction = friction;
    }

    /**
     * Gets the friction of this platform.
     * 
     * @return the friction of this platform
     */
    public double getFriction() {
        return friction;
    }

    // Bounce Methods //

    /**
     * Sets the bounciness of this platform.
     * 
     * @param bounce the desired bounciness of platform
     */
    public void setBounce(double bounce) {
        this.bounce = bounce;
    }

    /**
     * Get the bounciness of this platform.
     * 
     * @return the bounciness of the platform
     */
    public double getBounce() {
        return bounce;
    }

    // Intersection Methods //
    /**
     * Test whether an entity is intersecting this platform.
     * 
     * @param entity the entity to test
     * @return whether the entity is intersecting this platform
     */
    public boolean isIntersecting(Entity entity) {
        return this.hitBox.intersects(entity.hitBox);
    }

    /**
     * If an entity is intersecting this platform, get the direction they must
     * travel to leave the platform.
     * 
     * @param entity the entity to test
     * @return the direction the entity must travel to escape the bounds of this
     *         platform
     */

    @Override
    public void update() {

    }

    @Override
    public Point2D getIntersectEscape(Point2D point) {
        // Condtions //
        if (!isIntersecting(point))
            return new Point2D.Double(0, 0);
        // Data //
        double yOffset = point.getY() - hitBox.getCenterY();
        // Checks //
        if (yOffset == 0)
            yOffset = 1;
        // Settings //

        yOffset = (yOffset / Math.abs(yOffset)) * Constants.WorldSettings.worldGravity * getBounce();
        // Success //
        return new Point2D.Double(0, yOffset);
    }

    public boolean isIntersecting(Point2D point) {
        return this.hitBox.contains(point);
    }
}
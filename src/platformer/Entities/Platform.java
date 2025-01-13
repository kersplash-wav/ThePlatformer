package platformer.Entities;

// Imports //
import java.awt.geom.Point2D;
import java.awt.geom.Dimension2D;
import java.util.ArrayList;
import java.awt.Point;
import java.awt.Dimension;
import platformer.Entities.EntityConstants.PlatformConstants;
import platformer.Entities.EntityConstants.PlatformConstants.PresetPlatform;

// Class //
public class Platform extends Entity {
    // Public Data //
    public static final ArrayList<Platform> platformList = new ArrayList<Platform>();
    // Private Data //
    private double friction = PlatformConstants.StandardPlatform.friction;
    private double bounce = PlatformConstants.StandardPlatform.bounce;
    // Constructor //

    /**Creates a platform object.
     * @param position the position of the centre of the platform
     * @param size     the width and height of this platform
     */
    public Platform(Point2D position, Dimension2D size) {
        // Activate Parent Constructor //
        super(position, size);
        // Add Reference //
        platformList.add(this);
        setColour(PlatformConstants.StandardPlatform.colour);
    }

    /**Creates a platform object.
     * @param xPos   the x position of the centre of this platform
     * @param yPos   the y position of the centre of this platform
     * @param width  the width of this platform
     * @param height the height of this platform
     */
    public Platform(int xPos, int yPos, int width, int height) {
        // Activate Parent Constructor //
        this(new Point(xPos, yPos), new Dimension(width, height));
        setColour(PlatformConstants.StandardPlatform.colour);
    }

    // Friction Methods //

    /**Sets the friction of this platform.
     * @param friction the desired friction of this platform
     */
    public void setFriction(double friction) {
        this.friction = friction;
    }

    /**Gets the friction of this platform.
     * @return the friction of this platform
     */
    public double getFriction() {
        return friction;
    }

    // Bounce Methods //

    /**Sets the bounciness of this platform.
     * @param bounce the desired bounciness of platform
     */
    public void setBounce(double bounce) {
        this.bounce = bounce;
    }

    /**Get the bounciness of this platform.
     * @return the bounciness of the platform
     */
    public double getBounce() {
        return bounce;
    }

    @Override
    public void update() {
    }

    public void applyPreset(PresetPlatform preset){
        this.setSize(preset.width, preset.height);
        this.setBounce(preset.bounce);
        this.setFriction(preset.friction);
        this.setColour(preset.colour);
    }
}
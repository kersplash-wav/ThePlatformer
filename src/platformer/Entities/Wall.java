package platformer.Entities;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.awt.geom.Dimension2D;
import java.awt.Dimension;

import platformer.Entities.EntityConstants.WallConstants;

public class Wall extends Entity {
    // Static Data //
    /**
     * A list of all the walls
     */
    public static final ArrayList<Wall> wallList = new ArrayList<Wall>();
    /**
     * The bounce of this wall
     */
    private double bounce = WallConstants.StandardWall.bounce;
    /**
     * The friction of this wall
     */
    private double friction = WallConstants.StandardWall.friction;

    /**
     * The getter method for the friction
     * 
     * @return
     *         The friction of this wall
     */
    public double getFriction() {
        return friction;
    }

    /**
     * Sets the friction of this wall
     * 
     * @param friction
     *                 The amount of friction
     */
    public void setFriction(double friction) {
        this.friction = friction;
    }

    // Constructors //
    /**
     * Main constructor for the wall, used for constructor chaining
     * 
     * @param position
     *                 The position of this new wall
     * @param size
     *                 The size of this new wall
     */
    public Wall(Point2D position, Dimension2D size) {
        super(position, size);

        wallList.add(this);
        setColour(WallConstants.StandardWall.colour);

    }

    /**
     * Constructor for the wall
     * 
     * @param xPos The X-Axis Position
     * @param yPos The Y-Axis Position
     * @param width The width of the wall
     * @param height The height of the wall
     */
    public Wall(int xPos, int yPos, int width, int height) {
        // Activate Parent Constructor //
        this(new Point2D.Double(xPos, yPos), new Dimension(width, height));
    }

    /**
     * Constructor for the wall
     * 
     * @param xPos The X-Axis position
     * @param yPos The Y-Axis position
     */
    public Wall(int xPos, int yPos) {
        // Activate Parent Constructor //
        this(new Point2D.Double(xPos, yPos),
                new Dimension((int) WallConstants.StandardWall.width, (int) WallConstants.StandardWall.height));
    }

    /**
     * Get the bounciness of this wall
     * 
     * @return the bounciness of this wall
     */
    public double getBounce() {
        return bounce;
    }

    /**
     * Set the bounciness of this wall
     * 
     * @param bounce the amount of x velocity to apply when contacted
     */
    public void setBounce(double bounce) {
        this.bounce = bounce;
    }

    /**
     * Apply preset values to this wall
     * 
     * @param preset the preset to apply
     */
    public void applyPreset(WallConstants.PresetWall preset) {
        this.setSize(preset.width, preset.height);
        this.setBounce(preset.bounce);
        this.setFriction(preset.friction);
        this.setColour(preset.colour);
    }

}

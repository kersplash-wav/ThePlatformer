package platformer.Entities;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.awt.geom.Dimension2D;
import java.awt.Dimension;

import platformer.Entities.EntityConstants.WallConstants;

public class Wall extends Entity {
    // Static Data //
    public static final ArrayList<Wall> wallList = new ArrayList<Wall>();
    private double bounce = WallConstants.PresetWall.bounce;
    private double friction = WallConstants.PresetWall.friction;

    // Constructors //
    public Wall(Point2D position, Dimension2D size) {
        super(position, size);

        wallList.add(this);
        setColour(WallConstants.PresetWall.colour);

    }

    public Wall(int xPos, int yPos, int width, int height) {
        // Activate Parent Constructor //
        this(new Point2D.Double(xPos, yPos), new Dimension(width, height));
        setColour(WallConstants.PresetWall.colour);
    }

    public double getBounce() {
        return bounce;
    }

    public void setBounce(double bounce) {
        this.bounce = bounce;
    }

}

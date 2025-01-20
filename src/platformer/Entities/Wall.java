package platformer.Entities;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.awt.geom.Dimension2D;
import java.awt.Dimension;

import platformer.Entities.EntityConstants.PlatformConstants.PresetPlatform;
import platformer.Entities.EntityConstants.WallConstants;

public class Wall extends Entity {
    // Static Data //
    public static final ArrayList<Wall> wallList = new ArrayList<Wall>();
    private double bounce = WallConstants.StandardWall.bounce;
    private double friction = WallConstants.StandardWall.friction;

    public double getFriction() {
        return friction;
    }

    public void setFriction(double friction) {
        this.friction = friction;
    }

    // Constructors //
    public Wall(Point2D position, Dimension2D size) {
        super(position, size);

        wallList.add(this);
        setColour(WallConstants.StandardWall.colour);

    }

    public Wall(int xPos, int yPos, int width, int height) {
        // Activate Parent Constructor //
        this(new Point2D.Double(xPos, yPos), new Dimension(width, height));
    }

    
    public Wall(int xPos, int yPos) {
        // Activate Parent Constructor //
        this(new Point2D.Double(xPos, yPos), new Dimension((int)WallConstants.StandardWall.width, (int)WallConstants.StandardWall.height));
    }

    public double getBounce() {
        return bounce;
    }

    public void setBounce(double bounce) {
        this.bounce = bounce;
    }

    public void applyPreset(WallConstants.PresetWall preset){
        this.setSize(preset.width, preset.height);
        this.setBounce(preset.bounce);
        this.setFriction(preset.friction);
        this.setColour(preset.colour);
    }

}

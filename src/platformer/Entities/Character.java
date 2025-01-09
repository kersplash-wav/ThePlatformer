package platformer.Entities;

import platformer.Constants.PlayerSettings;
import java.awt.geom.Point2D;
import java.awt.Dimension;
import java.awt.geom.Dimension2D;

public class Character extends Entity {
    private Point2D movementAxis = new Point2D.Double(0, 0);

    public Character() {
        super(new Point2D.Double(0, 0), new Dimension(PlayerSettings.width, PlayerSettings.height));
    }

    private boolean isGroundedBy(Platform platform) {
        Point2D underPlayerL = new Point2D.Double(hitBox.getMinX(), hitBox.getMaxY());
        Point2D underPlayerR = new Point2D.Double(hitBox.getMaxX(), hitBox.getMaxY());
        if (!platform.getCollisionsEnabled())
            return false;
        if (platform.hitBox.contains(underPlayerL))
            return true;
        if (platform.hitBox.contains(underPlayerR))
            return true;
        return false;
    }

    public boolean isGrounded() {
        for (Platform platform : Platform.platformList) {
            if (isGroundedBy(platform))
                return true;
        }
        return false;
    }

    public Platform getGround() {
        for (Platform platform : Platform.platformList) {
            if (isGroundedBy(platform))
                return platform;
        }
        return null;
    }

    public boolean isRoofedBy(Platform platform) {
        Point2D abovePlayerL = new Point2D.Double(hitBox.getMinX(), hitBox.getMinY());
        Point2D abovePlayerR = new Point2D.Double(hitBox.getMaxX(), hitBox.getMinY());
        if (!platform.getCollisionsEnabled())
            return false;
        if (platform.hitBox.contains(abovePlayerL))
            return true;
        if (platform.hitBox.contains(abovePlayerR))
            return true;
        return false;
    }

    public boolean isRoofed() {
        for (Platform platform : Platform.platformList) {
            if (isRoofedBy(platform))
                return true;
        }
        return false;
    }

    public Platform getRoof() {
        for (Platform platform : Platform.platformList) {
            if (isRoofedBy(platform))
                return platform;
        }
        return null;
    }

    private boolean isLeftWalledBy(Wall wall) {
        Point2D underPlayerL = new Point2D.Double(hitBox.getMinX(), hitBox.getMaxY());
        Point2D abovePlayerL = new Point2D.Double(hitBox.getMinX(), hitBox.getMinY());
        if (!wall.getCollisionsEnabled())
            return false;
        if (wall.hitBox.contains(underPlayerL))
            return true;
        if (wall.hitBox.contains(abovePlayerL))
            return true;
        return false;
    }

    public boolean isLeftWalled() {
        for (Wall wall : Wall.wallList) {
            if (isLeftWalledBy(wall))
                return true;
        }
        return false;
    }

    public Wall getLeftWall() {
        for (Wall wall : Wall.wallList) {
            if (isLeftWalledBy(wall))
                return wall;
        }
        return null;
    }

    private boolean isRightWalledBy(Wall wall) {
        Point2D underPlayerR = new Point2D.Double(hitBox.getMaxX(), hitBox.getMaxY());
        Point2D abovePlayerR = new Point2D.Double(hitBox.getMaxX(), hitBox.getMinY());
        if (!wall.getCollisionsEnabled())
            return false;
        if (wall.hitBox.contains(underPlayerR))
            return true;
        if (wall.hitBox.contains(abovePlayerR))
            return true;
        return false;
    }

    public boolean isRightWalled() {
        for (Wall wall : Wall.wallList) {
            if (isRightWalledBy(wall))
                return true;
        }
        return false;
    }

    public Wall getRightWall() {
        for (Wall wall : Wall.wallList) {
            if (isRightWalledBy(wall))
                return wall;
        }
        return null;
    }

    @Override

    public void update() {

        // get current velocity
        Point2D velocity = getVelocity();

        // Don't constantly send through ground //
        setGravityEffect(!isGrounded());

        // apply gravity to velocity
        applyGravity();

        if (isGrounded())
            setVelocity(velocity.getX(), velocity.getY() + getGround().getBounce());

        // Movement Controls //
        setVelocity(
                new Point2D.Double(velocity.getX() + movementAxis.getX(), velocity.getY() + movementAxis.getY()));

        // Restrict Y Velocity to 10 downwards //
        if (velocity.getY() > 10)
            setVelocity(
                    new Point2D.Double(velocity.getX(), 10));

        // Restrict Y Velocity to 5 upwards //
        if (velocity.getY() < -5)
            setVelocity(
                    new Point2D.Double(velocity.getX(), -5));

        // Restrict X Velocity to 10 Right //
        if (velocity.getX() > 10)
            setVelocity(
                    new Point2D.Double(10, velocity.getY()));

        // Restrict X Velocity to 10 Left //
        if (velocity.getX() < -10)
            setVelocity(
                    new Point2D.Double(-10, velocity.getY()));

        // apply velocity to this object
        applyVelocity();

        // if moving downwards
        if (velocity.getY() >= 0) {
            // if touching ground
            if (isGrounded()) {
                // move to top of ground
                setPosition(getPosition().getX(), getGround().getPosition().getY() - getSize().getHeight() + 1);
            }
        }

        if (velocity.getX() < 0) {
            if (isLeftWalled()) {
                setVelocity(getLeftWall().getBounce(), velocity.getY());
                setPosition(getLeftWall().hitBox.getMaxX(), getPosition().getY());
            }
        }

        if (velocity.getX() > 0) {
            if (isRightWalled()) {
                setVelocity(getRightWall().getBounce(), velocity.getY());
                setPosition(getRightWall().hitBox.getMinX() - getSize().getWidth(), getPosition().getY());
            }
        }
    }

    public void setMovementAxis(Point2D point) {
        movementAxis = point;
    }
}
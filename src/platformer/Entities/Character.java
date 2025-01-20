package platformer.Entities;

import platformer.Entities.EntityConstants.CharacterConstants;
import java.awt.geom.Point2D;

import javax.swing.ImageIcon;

import java.awt.Dimension;
import java.awt.geom.Dimension2D;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.BasicStroke;

public class Character extends Entity {
    private ImageIcon characterImage = new ImageIcon("src/platformer/Gui/Images/characterRight.png");
    private Point2D movementAxis = new Point2D.Double(0, 0);

    public Character() {
        super(new Point2D.Double(0, 0), new Dimension((int)CharacterConstants.width, (int)CharacterConstants.height));
        setColour(CharacterConstants.colour);
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
            setVelocity(velocity.getX()*(movementAxis.getX() == 0?getGround().getFriction():1), velocity.getY() + getGround().getBounce());

        // Movement Controls //
        setVelocity(
                new Point2D.Double(velocity.getX() + movementAxis.getX(), velocity.getY() + movementAxis.getY()));

        // Restrict Y Velocity to 10 downwards //
        if (velocity.getY() > CharacterConstants.maxDownwardVelocity)
            setVelocity(
                    new Point2D.Double(velocity.getX(), CharacterConstants.maxDownwardVelocity));

        // Restrict Y Velocity to 5 upwards //
        if (velocity.getY() < CharacterConstants.maxUpwardVelocity)
            setVelocity(
                    new Point2D.Double(velocity.getX(), CharacterConstants.maxUpwardVelocity));

        // Restrict X Velocity to 10 Right //
        if (velocity.getX() > CharacterConstants.maxRightwardVelocity)
            setVelocity(
                    new Point2D.Double(CharacterConstants.maxRightwardVelocity, velocity.getY()));

        // Restrict X Velocity to 10 Left //
        if (velocity.getX() < CharacterConstants.maxLeftwardVelocity)
            setVelocity(
                    new Point2D.Double(CharacterConstants.maxLeftwardVelocity, velocity.getY()));

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
                setVelocity(getLeftWall().getBounce(), velocity.getY()+getLeftWall().getFriction());
                setPosition(getLeftWall().hitBox.getMaxX()-1, getPosition().getY());
            }
        }

        if (velocity.getX() > 0) {
            if (isRightWalled()) {
                setVelocity(-getRightWall().getBounce(), velocity.getY()+getRightWall().getFriction());
                setPosition(getRightWall().hitBox.getMinX() - getSize().getWidth()+1, getPosition().getY());
            }
        }
    }

    public void setMovementAxis(Point2D point) {
        movementAxis = point;
    }

    // Override Methods //
    @Override
    public void render(Graphics graphics) {
        render(graphics, new Point2D.Double());
    }

    @Override
    public void render(Graphics _graphics, Point2D offset) {
        Graphics2D graphics = (Graphics2D)_graphics;

        Point2D position = this.getPosition();
        Point2D velocity = this.getVelocity();
        boolean flipped = velocity.getX()<0;

        Dimension2D size = this.getSize();

        int xPos = (int) position.getX() + (int) offset.getX() + (int)((flipped ? 1 : 0) * size.getWidth());
        int yPos = (int) position.getY() + (int) offset.getY();

        int width = (int) size.getWidth() * (flipped ? -1 : 1);
        int height = (int) size.getHeight();

        graphics.setColor(getColour());
        graphics.drawImage(characterImage.getImage(), xPos, yPos, width, height, null);
//        graphics.fillRect(xPos, yPos, width, height);
        graphics.setColor(Color.black);
        graphics.setStroke(new BasicStroke(5));
        graphics.drawRect((int)position.getX() + (int)offset.getX(), yPos, Math.abs(width), height);
    }
}
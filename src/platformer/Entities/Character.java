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

    /**A character object that responds to user input and physics.
    */
    public Character() {
        super(new Point2D.Double(0, 0), new Dimension((int)CharacterConstants.width, (int)CharacterConstants.height));
        setColour(CharacterConstants.colour);
    }

    /**
     * @param platform the platform to test
     * @return whether the player is on this platform
     */
    private boolean isGroundedBy(Platform platform) {
        Point2D underPlayerL = new Point2D.Double(hitBox.getMinX(), hitBox.getMaxY());
        Point2D underPlayerR = new Point2D.Double(hitBox.getMaxX(), hitBox.getMaxY());
        if (platform.hitBox.contains(underPlayerL))
            return true;
        if (platform.hitBox.contains(underPlayerR))
            return true;
        return false;
    }

    /**
     * @return whether the player is on any platform
     */
    public boolean isGrounded() {
        for (Platform platform : Platform.platformList) {
            if (isGroundedBy(platform))
                return true;
        }
        return false;
    }

    /**
     * @return the platform that the player is currently on
     */
    public Platform getGround() {
        for (Platform platform : Platform.platformList) {
            if (isGroundedBy(platform))
                return platform;
        }
        return null;
    }


    /**
     * @param wall the wall to test
     * @return whether the left side of the player is hitting this wall 
     */
    private boolean isLeftWalledBy(Wall wall) {
        Point2D underPlayerL = new Point2D.Double(hitBox.getMinX(), hitBox.getMaxY());
        Point2D abovePlayerL = new Point2D.Double(hitBox.getMinX(), hitBox.getMinY());
        if (wall.hitBox.contains(underPlayerL))
            return true;
        if (wall.hitBox.contains(abovePlayerL))
            return true;
        return false;
    }

    
    /**
     * @return whether the left side of the player is hitting a wall
     */
    public boolean isLeftWalled() {
        for (Wall wall : Wall.wallList) {
            if (isLeftWalledBy(wall))
                return true;
        }
        return false;
    }

    /**
     * @return the wall that the left side of the player is hitting
     */
    public Wall getLeftWall() {
        for (Wall wall : Wall.wallList) {
            if (isLeftWalledBy(wall))
                return wall;
        }
        return null;
    }

    
    /**
     * @param wall the wall to test
     * @return whether the right side of the player is hitting this wall
     */
    private boolean isRightWalledBy(Wall wall) {
        Point2D underPlayerR = new Point2D.Double(hitBox.getMaxX(), hitBox.getMaxY());
        Point2D abovePlayerR = new Point2D.Double(hitBox.getMaxX(), hitBox.getMinY());
        if (wall.hitBox.contains(underPlayerR))
            return true;
        if (wall.hitBox.contains(abovePlayerR))
            return true;
        return false;
    }

    /**
     * @return whether the right side of the player is hitting a wall
     */
    public boolean isRightWalled() {
        for (Wall wall : Wall.wallList) {
            if (isRightWalledBy(wall))
                return true;
        }
        return false;
    }


    /**
     * @return the wall that the right side of the player is hitting
     */
    public Wall getRightWall() {
        for (Wall wall : Wall.wallList) {
            if (isRightWalledBy(wall))
                return wall;
        }
        return null;
    }


    @Override
    public void update() {

        Point2D velocity = getVelocity();
        setGravityEffect(!isGrounded());
        applyGravity();

        // Apply friction and bounce //
        if (isGrounded())
            setVelocity(velocity.getX()*(movementAxis.getX() == 0?getGround().getFriction():1), velocity.getY() + getGround().getBounce());

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


        applyVelocity();

        // Don't go through ground //
        if (velocity.getY() >= 0) {
            if (isGrounded()) {
                setPosition(getPosition().getX(), getGround().getPosition().getY() - getSize().getHeight() + 1);
            }
        }

        // Don't go through left walls //
        if (velocity.getX() < 0) {
            if (isLeftWalled()) {
                setVelocity(getLeftWall().getBounce(), velocity.getY()+getLeftWall().getFriction());
                setPosition(getLeftWall().hitBox.getMaxX()-1, getPosition().getY());
            }
        }

        // Don't go through right walls //
        if (velocity.getX() > 0) {
            if (isRightWalled()) {
                setVelocity(-getRightWall().getBounce(), velocity.getY()+getRightWall().getFriction());
                setPosition(getRightWall().hitBox.getMinX() - getSize().getWidth()+1, getPosition().getY());
            }
        }
    }

    /** Set the movement axis of this player, the 2D velocity that is constantly applied
     * @param point the 2D velocity to apply to the player
     */
    public void setMovementAxis(Point2D point) {
        movementAxis = point;
    }

    @Override
    /** Render the player to the screen
     * @param graphics the graphics to render this player to
     */
    public void render(Graphics graphics) {
        render(graphics, new Point2D.Double());
    }

    @Override
    /** Render the player to the screen using a camera offset
     * @param graphics the graphics to render this player too
     * @param offset the point to offset the player by
     */
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
        graphics.setColor(Color.black);
        graphics.setStroke(new BasicStroke(5));
        graphics.drawRect((int)position.getX() + (int)offset.getX(), yPos, Math.abs(width), height);
    }
}
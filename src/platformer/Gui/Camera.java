package platformer.Gui;

import platformer.Entities.Entity;
import java.awt.geom.Point2D;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;

public class Camera extends JPanel {
    // Instance Data //
    /**
     * The parent gui frame.
     */
    public final CoreFrame frame;
    /**
     * The position of the camera.
     */
    public Point2D position = new Point2D.Double();
    public ImageIcon background;

    // Constructor //
    /**
     * Constructs a new camera.
     * @param coreFrame
     * The parent frame which the camera will be a child of.
     */
    public Camera(CoreFrame coreFrame) {
        // Initialize Ancestor //
        super();
        this.frame = coreFrame;
        // Initialzie Data //
        this.setSize(frame.getSize());
        // Attach to Coreframe //
        frame.add(this);
    }

    // Base Methods //
    /**
     * Sets a new position for the camera.
     * @param position
     * The new position for the camera.
     */
    public void setPosition(Point2D position) {
        this.position = position;
    }

    /**
     * Gets the position of the camera
     * @return 
     * The position of the camera
     */
    public Point2D getPosition() {
        return position;
    }

    /**
     * Gets the offset of the camera
     * @return
     * Offset of the camera
     */
    public Point2D getOffset() {
        return new Point2D.Double(-getPosition().getX(), -getPosition().getY());
    }

    /**
     * Gets the parent frame of the camera
     * @return
     * The parent frame of the camera
     */
    public CoreFrame getFrame() {
        return frame;
    }

    /**
     * Sets the background image of the camera
     * @param img
     * The background image
     */
    public void setBackgroundImage(ImageIcon img){
        background = img;
    }
    // Override Methods //
    @Override
    public void paintComponent(Graphics graphics) {
        if(background!=null){
            Dimension size = getFrame().getSize();
            graphics.drawImage(background.getImage(), (int)(-position.getX()/10 - size.getWidth() / 2), (int)(-position.getY()/10 - size.getHeight() / 3), (int)size.getWidth()*2, (int)size.getHeight()*2,null);
        }
        Entity.renderAll(graphics, getOffset());
    }
}
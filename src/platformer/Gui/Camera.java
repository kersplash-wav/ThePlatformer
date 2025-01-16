package platformer.Gui;

import platformer.Entities.Entity;
import platformer.Services.Level;
import java.awt.geom.Point2D;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Graphics;

public class Camera extends JPanel {
    // Instance Data //
    public final CoreFrame frame;
    public Point2D position = new Point2D.Double();
    public ImageIcon background;

    // Constructor //
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
    public void setPosition(Point2D position) {
        this.position = position;
    }

    public Point2D getPosition() {
        return position;
    }

    public Point2D getOffset() {
        return new Point2D.Double(-getPosition().getX(), -getPosition().getY());
    }

    public CoreFrame getFrame() {
        return frame;
    }

    public void setBackgroundImage(ImageIcon img){
        background = img;
    }

    // Conversion Methods //
    // Override Methods //
    @Override
    public void paintComponent(Graphics graphics) {
        // if(background!=null && Level.currentLevel != null){
        //     Level.currentLevel.Update(graphics);
        // }
        Entity.renderAll(graphics, getOffset());
    }
}
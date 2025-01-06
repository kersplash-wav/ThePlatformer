package platformer.Gui;

import java.awt.geom.Point2D;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Point;

public class Camera extends JPanel {
    // Instance Data //
    public final CoreFrame frame;
    public Point2D position = new Point2D.Double();

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

    // Conversion Methods //
    // Override Methods //
    @Override
    public void paintComponent(Graphics graphics) {
    }
}
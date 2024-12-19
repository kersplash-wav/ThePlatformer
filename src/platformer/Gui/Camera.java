package platformer.Gui;

import java.awt.geom.Point2D;
import javax.swing.JPanel;

import platformer.Player;

import java.awt.Dimension;
import java.awt.Graphics;

public class Camera extends JPanel {
    // Instance Data //
    public final CoreFrame frame;
    public Point2D position = new Point2D.Double();
    public Dimension size;

    // Constructor //
    public Camera(CoreFrame frame, Dimension size) {
        // Initialize Ancestor //
        super();
        // Initialzie Data //
        this.size = size;
        this.frame = frame;
        this.setSize(size);
        // Settings //
        frame.add(this);
    }

    // Base Methods //
    // Encapsulation Methods //
    public void setPosition(Point2D position) {
        this.position = position;
    }

    public Point2D getPosition() {
        return position;
    }

    public CoreFrame getFrame() {
        return frame;
    }

    // Conversion Methods //
    public Point2D toGlobal(Point2D localPoint) {
        return new Point2D.Double(localPoint.getX() + position.getX(), localPoint.getY() + position.getY());
    }

    public Point2D toLocal(Point2D globalPoint) {
        return new Point2D.Double(globalPoint.getX() - position.getX(), globalPoint.getY() - position.getY());
    }

    // Override Methods //
    @Override
    public void paintComponent(Graphics graphics) {

        for (Player player : Player.playerlist) {
            player.render();
        }
    }
}
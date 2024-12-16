package platformer.Gui;

import java.awt.geom.Point2D;
import java.awt.geom.Dimension2D;
import java.awt.Graphics;

public class Camera {
    // Instance Data //
    public CoreFrame frame;
    public DrawCanvas canvas;
    public Point2D position = new Point2D.Double();
    public Dimension2D size;

    // Constructor //
    public Camera(Dimension2D size) {
        this.size = size;
    }

    // Base Methods //
    public void setPosition(Point2D position) {
        this.position = position;
    }

    public Point2D getPosition() {
        return position;
    }

    public CoreFrame getFrame() {
        return frame;
    }

    public DrawCanvas getCanvas() {
        return canvas;
    }

    public Graphics getGraphics() {
        return canvas.getGraphics();
    }

    public Point2D toGlobal(Point2D localPoint) {
        return new Point2D.Double(localPoint.getX() + position.getX(), localPoint.getY() + position.getY());
    }

    public Point2D toLocal(Point2D globalPoint) {
        return new Point2D.Double(globalPoint.getX() - position.getX(), globalPoint.getY() - position.getY());
    }
}
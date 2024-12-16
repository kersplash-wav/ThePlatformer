package platformer.Tools;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import platformer.Entities.Platform;
import platformer.Entities.PlayerCharacter;
import platformer.Gui.Camera;

public class Tool implements MouseListener {
    // Character Data //
    PlayerCharacter holder;
    Camera camera;
    // Data //
    private long deltaTime;
    private Raycast rayCast;

    // Constructors //
    public Tool(PlayerCharacter holder) {
        this.holder = holder;
        holder.equipTool(this);
        rayCast = new Raycast(holder.getPosition(), holder.getPosition(), deltaTime);
    }

    // Update Methods //
    public final void update(Graphics2D graphics, Camera camera) {
        // Camera //
        // Data //
        PointerInfo mouseData = getMouse();
        // Data //
        long currentTime = System.currentTimeMillis();
        // Activate //
        update(currentTime - deltaTime, graphics, camera);
        rayCast.globalOrigin = new Point2D.Double(holder.hitBox.getCenterX(), holder.hitBox.getCenterY());
        rayCast.lookAt(mouseData.getLocation());
        // Reset //
        deltaTime = currentTime;
    }

    // Private Methods //
    public void equipped() {

    }

    private void update(long deltaTime, Graphics2D graphics, Camera camera) {
        rayCast.display(graphics, camera);
    }

    private PointerInfo getMouse() {
        return MouseInfo.getPointerInfo();
    }

    // Input //
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

class Raycast {
    // Data //
    public Point2D globalOrigin;
    public Point2D direction;
    public double length;

    // Constructors //
    /**
     * @param origin
     * @param direction
     */
    public Raycast(Point2D globalOrigin, Point2D direction, double length) {
        // Initialize Settings //
        this.globalOrigin = globalOrigin;
        this.direction = direction;
        this.length = length;
        // Initialize Raycast Length //
        setMagnitude(length);
    }

    // Math Methods //
    private double getMagnitude() {
        return direction.distance(0, 0);
    }

    private void setMagnitude(double magnitude) {
        // Magnitude Data //
        double currentMagnitude = getMagnitude();
        // Calculations //
        double sizeFactor = magnitude / currentMagnitude;
        // Settings //
        direction.setLocation(direction.getX() * sizeFactor, direction.getY() * sizeFactor);
        // Debug //
        // System.out.println(currentMagnitude);
    }

    // Base Methods //
    private Line2D getLocalLine(Camera camera) {
        // Conversion //
        Point2D localOrigin = camera.toLocal(globalOrigin);
        Point2D localDestination = new Point2D.Double(localOrigin.getX() + direction.getX(),
                localOrigin.getY() + direction.getY());
        return new Line2D.Double(localOrigin, localDestination);
    }

    private Line2D getGlobalLine() {
        Point2D globalDestination = new Point2D.Double(globalOrigin.getX() + direction.getX(),
                globalOrigin.getY() + direction.getY());
        return new Line2D.Double(globalOrigin, globalDestination);
    }

    public Point2D lookAt(Point2D globalDestination) {
        // Get Offset //
        Point2D offset = new Point2D.Double();
        offset.setLocation(globalDestination.getX() - globalOrigin.getX(),
                globalDestination.getY() - globalOrigin.getY());
        // Settings //
        direction = offset;
        // Set Magnitude //
        setMagnitude(offset.distance(0, 0));
        // Return Copy //
        return (Point2D) direction.clone();
    }

    // Action Methods //
    public boolean cast(Camera camera, Graphics2D graphics) {
        for (Platform platform : Platform.platformList) {
            // Local //
            Line2D localLine = getLocalLine(camera);
            Point2D localOrigin = localLine.getP1();
            Point2D localDestination = localLine.getP2();
            // Global //
            Line2D globalLine = getGlobalLine();
            Point2D globalDestination = globalLine.getP2();
            // Hitbox //
            Rectangle2D hitBox = platform.hitBox.getFrame();
            // Debug //
            graphics.setColor(hitBox.intersectsLine(globalLine) ? Color.blue : Color.yellow);
            graphics.draw(globalLine);
            // Checks //
            if (hitBox.intersectsLine(globalLine))
                return true;
        }
        // Fail //
        return false;
    }

    // Visual Methods //
    public void display(Graphics2D graphics, Camera camera) {
        // Get Raycast Result //
        boolean success = cast(camera, graphics);
        // Draw Line //
        graphics.setColor(success ? Color.green : Color.red);
        graphics.draw(getLocalLine(camera));
    }
}
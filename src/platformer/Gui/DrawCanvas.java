package platformer.Gui;

import static platformer.Main.Cameras;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;

import javax.swing.JPanel;

import platformer.Entities.Platform;
import platformer.Main;
import platformer.Entities.Entity;

public class DrawCanvas extends JPanel {
    // Private Data //
    private final CoreFrame coreFrame;

    // Constructor //
    public DrawCanvas(CoreFrame coreFrame) {
        this.setSize(coreFrame.getSize());
        this.coreFrame = coreFrame;
    }

    public DrawCanvas(CoreFrame coreFrame, Dimension size) {
        this.coreFrame = coreFrame;
        this.setSize(size);
    }

    // Base Methods //
    @Override
    public void paintComponent(Graphics _graphics) // Gets ran every frame //
    {
        // Initialize //
        Graphics2D graphics = (Graphics2D) _graphics;
        // Display //
        GraphicsDevice display = coreFrame.display;
        // Camera //
        Camera camera = Cameras.get(display);
        // Set Background //
        // graphics.setColor(Color black);
        // Render Entities //
        camera.render(graphics);
        // Entity.renderAll(graphics);
    }
}
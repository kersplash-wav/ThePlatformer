package platformer.Gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;


import platformer.Entities.Platform;
import platformer.Entities.Entity;

public class DrawCanvas extends JPanel
{
    // Private Data //
    private final CoreFrame coreFrame;
    // Constructor //
    public DrawCanvas(CoreFrame coreFrame)
    {
        this.setSize(coreFrame.getSize());
        this.coreFrame = coreFrame;
    }
    // Base Methods //
    @Override
    public void paintComponent(Graphics _graphics) // Gets ran every frame //
    { 
        // Initialize //
        Graphics2D graphics = (Graphics2D)_graphics;
        // Set Background //
        //graphics.setColor(Color black);
        // Render Entities //
        Entity.renderAll(graphics);
    }
}
// Folder Address //
package platformer.Gui;

import java.awt.GraphicsDevice;
import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.*;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import java.util.ConcurrentModificationException;

import javax.swing.JFrame;

import platformer.Main;
import platformer.Entities.Entity;
import platformer.Entities.Player;

public class CoreFrame extends JFrame implements KeyListener
{
    // Data //
    public final DrawCanvas drawCanvas;
    public final GraphicsDevice display;
    public final GuiThread thread;
    // Constructor //
    public CoreFrame(GraphicsDevice display)
    {
        // Init //
        super(display.getDefaultConfiguration());
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setUndecorated(true);
        this.setVisible(true);
        this.frameInit();
        // Create Child //
        this.drawCanvas = new DrawCanvas(this);
        // Settings //
        this.setContentPane(drawCanvas);
        this.display = display;
        this.addKeyListener(this);
        // Activate Thread //
        this.thread = new GuiThread(this);
        // Add Reference //
        Main.CoreFrames.put(display, this);
        
    }
    // Inputs //
    @Override
    public void keyTyped(KeyEvent ke) {
        
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        
    }

    @Override
    public void keyReleased(KeyEvent keyData) {
                switch (keyData.getKeyCode())
                {
                    case VK_ESCAPE:
                        System.exit(0);
                    default:
                        break;
                }
    }
    // Subclass //
    public class GuiThread extends Thread
    {
        // Data //
        public final CoreFrame coreFrame;
        // Constructor //
        public GuiThread(CoreFrame coreFrame)
        {
            System.out.println("BEGIN THREAD!");
            // Attach Parent //
            this.coreFrame = coreFrame;
            // Begin Thread //
            this.start();
        }
        // Override Methods //
        @Override
        public void run()
        {
            System.out.println("Running!");
            while (true) 
            {
                // Checks //
                coreFrame.repaint();

                try 
                {
                    for (Entity entity : Entity.entityList)
                    {
                        try {
                            // Update Entity //
                            entity.update();
                            // Convert //
                            try
                            {
                                Player player = Player.class.cast(entity);
                                // Update Tools //
                                if (player != null)
                                    player.equippedTool.update((Graphics2D)coreFrame.getGraphics());
                            }

                            catch (Exception _) {}
                            // Delay //
                            this.sleep(1000/144);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                } catch (ConcurrentModificationException e)
                {
                    
                }
            }
        }
    }
}
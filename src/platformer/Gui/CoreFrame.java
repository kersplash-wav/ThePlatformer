// Folder Address //
package platformer.Gui;

import java.awt.GraphicsDevice;
import java.awt.Window;

import javax.swing.JFrame;

import java.util.Dictionary;
import java.util.Hashtable;

public class CoreFrame extends JFrame {
    // Data //
    public final GraphicsDevice display;
    public final GuiThread thread;
    public static final Dictionary<GraphicsDevice, CoreFrame> CoreFrames = new Hashtable<>();

    // Constructor //
    public CoreFrame(GraphicsDevice display) {
        // Init //
        super(display.getDefaultConfiguration());
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setUndecorated(true);
        this.setVisible(true);
        this.frameInit();
        // Settings //
        this.display = display;
        // Activate Thread //
        this.thread = new GuiThread(this);
        // Add Reference //
        CoreFrames.put(display, this);
    }

    public static CoreFrame getCoreFrame(GraphicsDevice display) {
        return CoreFrames.get(display);
    }

    // Subclass //
    public class GuiThread extends Thread {
        // Data //
        public final CoreFrame coreFrame;

        // Constructor //
        public GuiThread(CoreFrame coreFrame) {
            // Attach Parent //
            this.coreFrame = coreFrame;
            // Begin Thread //
            this.start();
        }

        // Override Methods //
        @Override
        public void run() {
            while (true) {
                coreFrame.repaint();
                try {
                    Thread.sleep(1000 / 144);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
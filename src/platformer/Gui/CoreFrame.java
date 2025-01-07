// Folder Address //
package platformer.Gui;

import java.awt.GraphicsDevice;
import java.awt.Graphics;
import javax.swing.JFrame;

public class CoreFrame extends JFrame {
    // Data //
    public final GuiThread thread;

    // Constructor //
    public CoreFrame(GraphicsDevice display) {
        // Init //
        super(display.getDefaultConfiguration());
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setUndecorated(true);
        this.setVisible(true);
        this.frameInit();
        // Activate Thread //
        this.thread = new GuiThread(this);
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
                try {
                    Thread.sleep(1000 / 144);
                    coreFrame.repaint();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
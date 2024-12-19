// Directory //
package platformer.Services;

// Imports //
import java.util.Dictionary;
import java.util.Hashtable;

import platformer.Main;
import platformer.Gui.CoreFrame;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.*;
import java.awt.GraphicsDevice;

// Main Class //
public class InputService implements KeyListener {
    // Data //
    private final Dictionary<Integer, Boolean> KeysDown = new Hashtable<>();
    private static InputService Service;
    // Constructor //
    static {
        Service = new InputService();
        for (GraphicsDevice display : Main.displays) {
            CoreFrame.getCoreFrame(display);
        }
    }

    // Base Methods //
    public static InputService GetInputService() {
        return Service;
    }

    // Action Methods //
    public boolean IsKeyDown(int keyCode) {
        try {
            return KeysDown.get(keyCode);
        }

        catch (NullPointerException exception) {
            return false;
        }
    }

    // Override Methods //
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent keyData) {
        // Data //
        int keyCode = keyData.getKeyCode();
        // Add Reference //
        KeysDown.put(keyCode, true);
        // Custom Listeners //
        switch (keyCode) {
            case VK_ESCAPE:
                System.exit(0);
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent keyData) {
        // Data //
        int keyCode = keyData.getKeyCode();
        // Set Reference //
        KeysDown.put(keyCode, false);
    }
}
// Directory //
package platformer.Services;

// Imports //
import java.util.Dictionary;
import java.util.Hashtable;

import platformer.Main;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.*;

// Main Class //
public class InputService implements KeyListener {
    // Data //
    /**
     * A dictionary that says whether a key is down or not
     */
    private final Dictionary<Integer, Boolean> KeysDown = new Hashtable<>();
    private static InputService Service;
    // Constructor //
    /**
     * A static constructor which initializes the singleton InputService
     */
    static
    {
        Service = new InputService();
        Main.coreFrame.addKeyListener(Service);
    }

    // Base Methods //
    /**
     * Getter method for the InputService singleton
     * @return
     * The InputService
     */
    public static InputService GetInputService() {
        return Service;
    }

    // Action Methods //
    /**
     * Method for checking whether a certain key is down
     * @param keyCode
     * The keycode of the key which will be checked
     * @return
     * If the key is down
     */
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
                break;
                case VK_BACK_SPACE:
                Main.loadLevel(0);
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
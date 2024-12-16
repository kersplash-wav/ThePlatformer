// Directory //
package platformer.Services;
// Imports //
import java.util.Dictionary;
import java.util.Hashtable;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.*;
// Main Class //
public class InputService implements KeyListener
{
    // Data //
    public final Dictionary<Integer, Boolean> KeysDown = new Hashtable<>();
    // Override Methods //
    @Override
    public void update() {
        // Data //
        Point2D velocity = this.getVelocity();
        // Physics //
        updatePhysics();
        // Ground //
        if (isGrounded())
        {
            System.out.println("GROUNDED!");
        }
        // Movemement //
        velocity.setLocation(movementAxis.getX(), velocity.getY());
    }

    // Abstract Methods //
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent keyData) 
    {
        // Data //
        int keyCode = keyData.getKeyCode();
        // Conditions //
        if (KeysDown.get(keyCode))
            return;
        else
            KeysDown.put(keyCode, true);
        // Result //
        switch (keyCode) {
            case VK_UP:
                movementAxis.translate(0, -1);
                break;
            
            case VK_DOWN:
                movementAxis.translate(0, 1);
                break;

            case VK_LEFT:
                movementAxis.translate(-1, 0);
                break;
            
            case VK_RIGHT:
                movementAxis.translate(1, 0);
                break;

            case VK_SPACE:
                setVelocity(getVelocity().getX(), -5);
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent keyData) 
    {
        // Data //
        int keyCode = keyData.getKeyCode();
        // Conditions //
        if (!keysDown.contains(keyCode))
            return;
        else
            keysDown.remove(keysDown.indexOf(keyCode));
        // Result //
        switch (keyData.getKeyCode()) {
            case VK_UP:
                movementAxis.translate(0, 1);
                break;
            
            case VK_DOWN:
                movementAxis.translate(0, -1);
                break;

            case VK_LEFT:
                movementAxis.translate(1, 0);
                break;
            
            case VK_RIGHT:
                movementAxis.translate(-1, 0);
                break;

            default:
                break;
        }
    }
}
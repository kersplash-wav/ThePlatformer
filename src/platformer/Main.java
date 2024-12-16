/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

// Controller Clas https://jinput.github.io/jinput/

package platformer;

import platformer.Controller.*;
import javax.swing.JOptionPane;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.*;
import java.awt.event.KeyListener;
import java.util.Dictionary;
import java.util.Hashtable;
import java.awt.geom.Point2D;
import java.awt.Color;

import platformer.Entities.Platform;
import platformer.Entities.PlayerCharacter;
import platformer.Gui.Camera;
import platformer.Gui.CoreFrame;
import platformer.Tools.Tool;
import platformer.Constants.*;

/**
 *
 * @author: Nicholas Ranin
 * @date: Nov 27, 2024
 *        filename: Platformer.java
 * @description: Fill In!
 */

public class Main {
    // Graphics //
    public static final GraphicsEnvironment GE = GraphicsEnvironment.getLocalGraphicsEnvironment();
    public static final GraphicsDevice[] displays = GE.getScreenDevices();
    public static final Dictionary<GraphicsDevice, CoreFrame> CoreFrames = new Hashtable<>();
    public static final Dictionary<GraphicsDevice, Camera> Cameras = new Hashtable<>();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        /* Create an event object for the underlying plugin to populate */
          Event event = new Event();

        /* Get the available controllers */
        Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();
        for (int i = 0; i < controllers.length; i++) {
            System.out.println("Controller Found!");
            /* Remember to poll each one */
            controllers[i].poll();

            /* Get the controllers event queue */
            EventQueue queue = controllers[i].getEventQueue();

            /* For each object in the queue */
            while (queue.getNextEvent(event)) {
                /* Get event component */
                Component comp = event.getComponent();

                /* Process event (your awesome code) */
                
            }
        }
        new Platform(500, 500, 500, 100);
        Platform platform2 = new Platform(-500, 500, 500, 100);
        platform2.setColour(Color.GREEN);
        platform2.setBounce(Constants.Platform.RubberPlatform.bounce);
        // TODO code application logic here

        for (GraphicsDevice display : displays) 
        {
            // Initialize Gui //
            CoreFrame coreFrame = new CoreFrame(display);
            // Initialize Player //
            Player playerOne = new Player(1,display, this)
            player.setGravityEffect(true);
            // Initialize Camera //
            Camera camera = new Camera(display, player);
            // Initialize Textures (idk if this does anything)
            // Texture texture = new Texture();
            // Debug //
            System.out.println(display);
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

// Controller Clas https://jinput.github.io/jinput/

package platformer;

import javax.swing.JOptionPane;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.geom.Point2D;
import java.awt.Color;

import platformer.Entities.Platform;
import platformer.Gui.CoreFrame;

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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // /* Create an event object for the underlying plugin to populate */
        // Event event = new Event();

        // /* Get the available controllers */
        // Controller[] controllers =
        // ControllerEnvironment.getDefaultEnvironment().getControllers();
        // for (int i = 0; i < controllers.length; i++) {
        // System.out.println("Controller Found!");
        // /* Remember to poll each one */
        // controllers[i].poll();

        // /* Get the controllers event queue */
        // EventQueue queue = controllers[i].getEventQueue();

        // /* For each object in the queue */
        // while (queue.getNextEvent(event)) {
        // /* Get event component */
        // Component comp = event.getComponent();

        // /* Process event (your awesome code) */

        // }
        // }
        Platform platform1 = new Platform(500, 500, 500, 100);
        Platform platform2 = new Platform(-500, 500, 500, 100);
        platform2.setColour(Color.GREEN);
        platform2.setBounce(Constants.Platform.RubberPlatform.bounce);
        // TODO code application logic here

        for (GraphicsDevice display : displays) {
            CoreFrame coreFrame = new CoreFrame(display);
            int count;
            // Initialize Option Pane //
            while (true) {
                try {
                    String userInput = JOptionPane.showInputDialog(coreFrame,
                            "How many players on this screen (Max 4)?");
                    count = Integer.parseInt(userInput);
                    if (count > 4 || count < 1)
                        continue;
                } catch (Exception e) {
                    continue;
                }
                break;
            }
            // Initialize Player //
            for (int i = 0; i < count; i++) {
                Player player = new Player(display, new Point2D.Double(), display.getFullScreenWindow().getSize());
            }
            // Debug //
            System.out.println(display);
        }
    }
}

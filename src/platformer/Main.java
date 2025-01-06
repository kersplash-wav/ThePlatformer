/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

// Controller Clas https://jinput.github.io/jinput/

package platformer;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Color;

import platformer.Entities.Platform;
import platformer.Gui.Camera;
import platformer.Gui.CoreFrame;

/**
 *
 * @author: Nicholas Ranin
 * @date: Nov 27, 2024
 *        filename: Platformer.java
 * @description: Fill In!
 */

public class Main {

    public static Camera camera;
    public static Player player;

    // Graphics //
    public static GraphicsDevice display;
    public static CoreFrame coreFrame;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // Init Graphics //
        display = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        coreFrame = new CoreFrame(display);

        // Init Player //
        camera = new Camera(coreFrame);
        player = new Player();

        // Init Platforms //
        Platform platform1 = new Platform(500, 500, 500, 100);
        Platform platform2 = new Platform(-500, 500, 500, 100);
        platform2.setColour(Color.GREEN);
        platform2.setBounce(Constants.Platform.RubberPlatform.bounce);
    }
}

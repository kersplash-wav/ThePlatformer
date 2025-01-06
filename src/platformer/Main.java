/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

// Controller Clas https://jinput.github.io/jinput/

package platformer;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.geom.Point2D;
import java.awt.Color;
import java.awt.Graphics;

import platformer.Entities.Entity;
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

    // Player //
    public static Camera camera;
    public static Player player;

    // Graphics //
    public static GraphicsDevice display;
    public static CoreFrame coreFrame;
    public static Graphics graphics;

    private static Point2D offset;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        init();

        while (true) {
            periodic();
        }
    }

    public static void init() {
        // Init Graphics //
        display = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        coreFrame = new CoreFrame(display);

        // Init Player //
        camera = new Camera(coreFrame);
        player = new Player();
        graphics = camera.getGraphics();

        // Init Platforms //
        Platform platform1 = new Platform(-250, 500, 500, 100); // x, y, width, height
        Platform platform2 = new Platform(-500, 500, 500, 100);
        platform1.setColour(Color.RED);
        platform2.setColour(Color.GREEN);
        platform2.setBounce(Constants.Platform.RubberPlatform.bounce);
    }

    public static void periodic() {
        Point2D playerPosition = player.getPosition();
        camera.setPosition(new Point2D.Double(
                playerPosition.getX() - coreFrame.getWidth() / 2,
                playerPosition.getY() - coreFrame.getHeight() / 2));
        offset = camera.getOffset();
        Entity.renderAll(graphics, offset);
    }
}

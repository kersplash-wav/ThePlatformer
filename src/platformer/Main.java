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

import platformer.Entities.Wall;
import platformer.Entities.Entity;
import platformer.Entities.EntityConstants;
import platformer.Entities.Platform;
import platformer.Gui.Camera;
import platformer.Gui.CoreFrame;
import platformer.Services.GuiService;

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

    public static double minX = -1000;
    public static double maxX = 1000;
    public static double minY = -1000;
    public static double maxY = 1000;

    private static Point2D offset;

    public static void main(String[] args) throws InterruptedException {

        init();

        while (true) {
            periodic();
            Thread.sleep(1000 / 144);
        }
    }

    public static void init() {
        // Init Graphics //
        display = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        coreFrame = new CoreFrame(display);

        // Init Player //
        camera = new Camera(coreFrame);
        player = new Player();
        
        GuiService.drawLevelOne(graphics);

        // Init Platforms //
        // Platform platform1 = new Platform(-250, 400, 500, 12); // x, y, width, height
        // platform1.applyPreset(new EntityConstants.PlatformConstants.RubberPlatform());

        // Platform platform2 = new Platform(-500, 600, 500, 12);
        // platform2.applyPreset(new EntityConstants.PlatformConstants.RubberPlatform());

        // Platform platform3 = new Platform(-750, 800, 500, 12);

        // Platform ground = new Platform(-1500, 1000, 3000, 200);

        // Wall wall1 = new Wall(-900, 500, 20, 1000);

        // wall1.setBounce(5);
        // Wall wall2 = new Wall(800, 500, 100, 500);
    }

    public static void periodic() {
        Point2D playerPosition = player.getPosition();
        camera.setPosition(new Point2D.Double(
                playerPosition.getX() - coreFrame.getWidth() / 2,
                playerPosition.getY() - coreFrame.getHeight() / 2));
        offset = camera.getOffset();
        player.periodic();
        // Entity.renderAll(graphics, offset);

        if (playerPosition.getY() > maxY) {
            player.character.setVelocity(new Point2D.Double());
            player.character.setPosition(playerPosition.getX(), minY+1);
        }
        if (playerPosition.getX() > maxX) {
            player.character.setVelocity(0, player.character.getVelocity().getY());
            player.character.setPosition(maxX, playerPosition.getY());
        }
        if (playerPosition.getX() < minX) {
            player.character.setVelocity(0, player.character.getVelocity().getY());
            player.character.setPosition(minX, playerPosition.getY());
        }

        Entity.updateAll();
    }

    public void reset(){
        Platform.platformList.clear();
        Entity.entityList.clear();
        Entity.entityList.add(player.character);
    }

    public static void setFrame(double minX, double maxX, double minY, double maxY){
        Main.maxX = maxX;
        Main.minX = minX;
        Main.maxY = maxY; 
        Main.minY = minY;
    }
}

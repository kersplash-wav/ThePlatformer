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
import java.awt.Font;
import java.awt.Graphics;

import platformer.Entities.Wall;
import platformer.Entities.Entity;
import platformer.Entities.EntityConstants.*;
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

    public static Point2D spawn = new Point2D.Double();

    private static Platform endPlatform;
    private static int currentLevel = 1;
    private static int levelCount = 3;

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
        graphics = coreFrame.getGraphics();

        // Init Player //
        camera = new Camera(coreFrame);
        player = new Player();
        
        levelOne();
        //GuiService.drawLevelOne(graphics);

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
        applyBoundaries();
        Entity.updateAll();
        if(player.character.getGround() == endPlatform){
            currentLevel++;
            if(currentLevel>levelCount)
                currentLevel = 1;
            loadLevel(currentLevel);
        }
    }

    public static void reset(){
        Platform.platformList.clear();
        Entity.entityList.clear();
        Entity.entityList.add(player.character);
        player.character.setVelocity(new Point2D.Double());
        player.character.setPosition(spawn);
        graphics.setFont(new Font("Arial", Font.BOLD, 24));
    }

    /**Set the player boundary
     * @param minX
     * @param maxX
     * @param minY
     * @param maxY
     */
    public static void setBoundaries(double minX, double maxX, double minY, double maxY){
        Main.maxX = maxX;
        Main.minX = minX;
        Main.maxY = maxY; 
        Main.minY = minY;
    }

    public static void applyBoundaries(){
        Point2D playerPosition = player.getPosition();
        if (playerPosition.getY() > maxY) {
            player.character.setVelocity(new Point2D.Double());
            player.character.setPosition(spawn);
        }
        if (playerPosition.getY() < minY) {
            player.character.setVelocity(player.character.getVelocity().getX(), 0);
            player.character.setPosition(playerPosition.getY(), minY);
        }
        if (playerPosition.getX() > maxX) {
            player.character.setVelocity(0, player.character.getVelocity().getY());
            player.character.setPosition(maxX, playerPosition.getY());
        }
        if (playerPosition.getX() < minX) {
            player.character.setVelocity(0, player.character.getVelocity().getY());
            player.character.setPosition(minX, playerPosition.getY());
        }
    }

    public static void setPlayerSpawn(double x, double y){
        spawn = new Point2D.Double(x,y);
    }

    public static void loadLevel(int level){
        switch(level){
            case 1:
            levelOne();    
            break;
            case 2:
            levelTwo();
            break;          
            case 3:
            levelThree();
            break;
        }
    }

    private static void levelOne(){
        setBoundaries(0, 5000, -1000, 1000);
        setPlayerSpawn(0, -200);
        reset();

        new Platform(0, 0).applyPreset(PlatformConstants.StandardPlatform);
        new Platform(300, -200).applyPreset(PlatformConstants.StandardPlatform);
        new Platform(600, -400).applyPreset(PlatformConstants.RubberPlatform);
        new Platform(1200, -400).applyPreset(PlatformConstants.RubberPlatform);
        endPlatform = null;
        endPlatform = new Platform(1800, -600);
        endPlatform.applyPreset(PlatformConstants.EndPlatform);
    }

    private static void levelTwo(){
        setBoundaries(0, 5000, -1000, 1000);
        setPlayerSpawn(0, -200);
        reset();
        new Platform(0, 0).applyPreset(PlatformConstants.StandardPlatform);
        new Platform(300, 200).applyPreset(PlatformConstants.StandardPlatform);
        new Platform(1200, 000).applyPreset(PlatformConstants.RubberPlatform);
        new Platform(1500, -200).applyPreset(PlatformConstants.RubberPlatform);
        new Platform(1200, -400).applyPreset(PlatformConstants.RubberPlatform);

        endPlatform = null;
        endPlatform = new Platform(600, -300);
        endPlatform.applyPreset(PlatformConstants.EndPlatform);
    }

    private static void levelThree(){
        setBoundaries(0, 5000, -1000, 1000);
        setPlayerSpawn(0, -700);
        reset();
        new Platform(0, -500).applyPreset(PlatformConstants.StandardPlatform);
        new Platform(300, -200).applyPreset(PlatformConstants.RubberPlatform);
        new Platform(600, 0).applyPreset(PlatformConstants.RubberPlatform);
        new Platform(300, 200).applyPreset(PlatformConstants.RubberPlatform);
        new Platform(600, 400).applyPreset(PlatformConstants.RubberPlatform);
        endPlatform = null;
        endPlatform = new Platform(450, 600);
        endPlatform.applyPreset(PlatformConstants.EndPlatform);
    }
}

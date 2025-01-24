package platformer;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.geom.Point2D;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;

import platformer.Entities.Wall;
import platformer.Entities.Entity;
import platformer.Entities.EntityConstants.*;
import platformer.Entities.Platform;
import platformer.Gui.Camera;
import platformer.Gui.CoreFrame;

/**
 * @author Nicholas Ranin, Joachim Michalef, Robert Rodriguez, Isaac MacKenzie
 * @date January 23, 2025
 * @filename Platformer.java
 * @description Our final project for ICS4U Computer Science.
 */

public class Main {

    // Player //
    public static Camera camera;
    public static Player player;

    // Graphics //
    /**
     * The monitor which the player is using.
     */
    public static GraphicsDevice display;
    /**
     * The Parent GUI frame which the game takes place on.
     */
    public static CoreFrame coreFrame;
    /**
     * The graphics object which is used to paint in paintComponent for the camera
     */
    public static Graphics graphics;
    /**
     * The most to the left a player can go
     */
    public static double minX = -1000;
    /**
     * The most to the right a player can go
     */
    public static double maxX = 1000;
    /**
     * The most to up a player can go
     */
    public static double minY = -1000;
    /**
     * The most down a player can go
     */
    public static double maxY = 1000;
    /**
     * Spawnpoint for the player
     */
    public static Point2D spawn = new Point2D.Double();
    /**
     * Platform which progresses to the next level
     */
    private static Platform endPlatform;
    /**
     * Current Level Index
     */
    private static int currentLevel = 0;
    /**
     * The Main Method of the program, is the first thing that runs.
     * @param args
     * @throws InterruptedException
     * For thread sleeping
     */
    public static void main(String[] args) throws InterruptedException {

        init();

        while (true) {
            periodic();
            Thread.sleep(1000 / 144);
        }
    }

    /**
     * First thing that's run in the main method
     */
    public static void init() {
        // Init Graphics //
        display = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        coreFrame = new CoreFrame(display);
        graphics = coreFrame.getGraphics();

        // Init Player //
        camera = new Camera(coreFrame);
        player = new Player();

        loadLevel(currentLevel);
    }

    /**
     * A method that's periodically run in the main method
     */
    public static void periodic() {
        Point2D playerPosition = player.getPosition();
        camera.setPosition(new Point2D.Double(
                playerPosition.getX() - coreFrame.getWidth() / 2,
                playerPosition.getY() - coreFrame.getHeight() / 2));
        player.periodic();
        applyBoundaries();
        Entity.updateAll();
        if (player.character.getGround() == endPlatform && endPlatform != null) {
            currentLevel++;
            loadLevel(currentLevel);
        }
    }

    /**
     * resets all entities between levels
     */
    public static void reset() {
        Platform.platformList.clear();
        Entity.entityList.clear();
        Entity.entityList.add(player.character);
        player.character.setVelocity(new Point2D.Double());
        player.character.setPosition(spawn);
        graphics.setFont(new Font("Arial", Font.BOLD, 24));
    }

    /**
     * Set the player boundary
     * 
     * @param minX
     * The most a player can move to the left
     * @param maxX
     * The most a player can move to the right
     * @param minY
     * The most a player can move up
     * @param maxY
     * The most a player can move down
     */
    public static void setBoundaries(double minX, double maxX, double minY, double maxY) {
        Main.maxX = maxX;
        Main.minX = minX;
        Main.maxY = maxY;
        Main.minY = minY;
    }

    /**
     * Updates the boundaries, bringing the player back if outside.
     */
    public static void applyBoundaries() {
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

    /**
     * @param x
     * X-Axis Position for player spawning point.
     * @param y
     * Y-Axis Position for player spawning point.
     */
    public static void setPlayerSpawn(double x, double y) {
        spawn = new Point2D.Double(x, y);
    }


    /**
     * Loads a level
     * @param level
     * The Level Index
     */
    public static void loadLevel(int level) {
        currentLevel = level;
        switch (level) {
            case 0:
                mainScreen();
                break;
            case 1:
                levelOne();
                break;
            case 2:
                levelTwo();
                break;
            case 3:
                levelThree();
                break;
            default:
                winScreen();
                break;
        }
    }

    /**
     * Main Screen, first screen seen in the game.
     */
    private static void mainScreen() {
        camera.setBackgroundImage(new ImageIcon("src/platformer/Gui/Images/purpleTitle.png")); // src/platformer/Gui/Images/blueSkyBackground.png
        setPlayerSpawn(50, -150);
        setBoundaries(-1000, 1000, -1000, 1000);
        reset();
        new Platform(0, 0).applyPreset(PlatformConstants.StandardPlatform);
        new Platform(400, 0).applyPreset(PlatformConstants.RubberPlatform);
        endPlatform = null;
        endPlatform = new Platform(800, 0);
        endPlatform.applyPreset(PlatformConstants.EndPlatform);
    }


    /**
     * Level One
     */
    private static void levelOne() {

        camera.setBackgroundImage(new ImageIcon("src/platformer/Gui/Images/blueSkyBackground.png"));
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

    /**
     * Level Two
     */
    private static void levelTwo() {
        camera.setBackgroundImage(new ImageIcon("src/platformer/Gui/Images/redSkyBackground.png"));

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

    /**
     * Level Three
     */
    private static void levelThree() {
        camera.setBackgroundImage(new ImageIcon("src/platformer/Gui/Images/purpleSkyBackground.png"));
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

    /**
     * Last Screen, declares the player a winner!
     */
    private static void winScreen() {
        camera.setBackgroundImage(new ImageIcon("src/platformer/Gui/Images/greenWin.png"));
        setPlayerSpawn(0, -200);
        setBoundaries(-2000, 2000, -500, 500);
        reset();
        Wall leftWall = new Wall(-2500, -1000);
        Wall rightWall = new Wall(1500, -1000);

        leftWall.applyPreset(WallConstants.RubberWall);
        rightWall.applyPreset(WallConstants.RubberWall);

        leftWall.setSize(1000, 1000);
        rightWall.setSize(1000, 1000);

        Platform platform = new Platform(-2500, 0, 5000, 1000);
        platform.setBounce(PlatformConstants.RubberPlatform.bounce);
        platform.setColour(PlatformConstants.RubberPlatform.colour);

        endPlatform = null;
    }
}
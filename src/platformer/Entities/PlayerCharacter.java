// Folder Address //
package platformer.Entities;

// Imports //
import platformer.Entities.Entity;
import platformer.Gui.CoreFrame;
import platformer.Tools.Tool;
import platformer.Main;
import platformer.Constants.PlayerSettings;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Enumeration;
import static platformer.Entities.Platform.platformList;

import static java.awt.event.KeyEvent.*;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Dimension2D;
import java.awt.geom.Line2D;

import static platformer.Main.CoreFrames;
import static platformer.Main.displays;

// Base Class //
public class PlayerCharacter extends Entity implements KeyListener {
    // Data //
    public Tool equippedTool;
    private final Point movementAxis = new Point(0, 0);
    public final ArrayList<Integer> keysDown = new ArrayList<Integer>();
    // Constructors //
    public PlayerCharacter(Point2D position, Dimension2D size) {
        super(position, size);
        CoreFrames.get(displays[0]).addKeyListener(this);
    }
    
    public PlayerCharacter(Point2D position)
    {
        this(position, new Dimension(PlayerSettings.width, PlayerSettings.height));
    }

    public PlayerCharacter(int xPos, int yPos, int width, int height) {
        // Activate Parent Constructor //
        this(new Point2D.Double((double) xPos, (double) yPos), new Dimension(width, height));
    }
    // Base Methods //
    public boolean isGrounded()
    {
        // Character Data //
        Dimension2D playerSize = getSize();
        Point2D underPlayer = new Point2D.Double(hitBox.getCenterX(), hitBox.getCenterY() + playerSize.getHeight() / 2);
        // Check through every platform //
        for (Platform platform : platformList)
        {
            // Conditions //
            if (platform.hitBox.contains(underPlayer))
            {
                // Debug //
                /*
                System.out.println("Detecting!");
                setPosition(getPosition().getX(), platform.getPosition().getY() - playerSize.getHeight());
                */
                // Experimental // 
                Point2D bounce = platform.getIntersectEscape(underPlayer);
                System.out.println(bounce.getY());
                setVelocity(getVelocity().getX()+bounce.getX(), bounce.getY());
                // Success //
                return true;
            }
            // Fail //
        }
        return false;
    }
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
    // Encapsulation //

    public void setPlayerClass() {
        // have options such as mage, warrior, ranger, summoner...
    }

    public String getPlayerClass() {
        return "";
    }

    public static void abilities() {
        // associate each class with abilities

        String[] mageUtil = {"windburst", "soul cleave"};
        String[] warriorUtil = {"grandslam", "adrenaline"};
        String[] rangerUtil = {"enhanced focus", "powerShot"};
        String[] summonerUtil = {"rejuvenating feed", "Infernal whip"};
    }

    // method for armour and accessories
    public static void equipment() 
    {
        String[] helmet = {"leather", "metal", "royal"};
        String[] chestplate = {"cloth", "leather", "chainmail", "metal", "royal"};
        String[] leggings = {"cloth", "leather", "chainmail", "metal", "royal"};
    }

    // method for weapon type
    public static void weapons() {
        String[] melee = {"sword", "katana", "broadsword", "battleAxe", "hammer", "mace"};
        String[] magic = {"spellBook", "wand", "staff"};
        String[] guns = {"rifle", "assultRifle", "pistol", "rayGun", "shotgun", "sniper"};
    }

    // tracks and displays player level
    public static void rank() {
        String[] rank = {"novice", "intermediate", "experienced", "expert"};
    }
    // Tools //
    public void equipTool(Tool tool)
    {
        equippedTool = tool;
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
        if (keysDown.contains(keyCode))
            return;
        else
            keysDown.add(keyCode);
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
// Folder Address //
package platformer.Entities;

// Imports //
import platformer.Entities.Entity;
import platformer.Gui.CoreFrame;
import platformer.Main;
import platformer.Constants.PlayerSettings;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Enumeration;

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
public class Player extends Entity implements KeyListener {
    // Data //
    private final Point movementAxis = new Point(0, 0);
    public final ArrayList<Integer> keysDown = new ArrayList<Integer>();
    // Constructors //
    public Player(Point2D position)
    {
        super((int)position.getX(), (int)position.getY(), PlayerSettings.width, PlayerSettings.height);
        CoreFrames.get(displays[0]).addKeyListener(this);
    }

    public Player(Point2D position, Dimension2D size) {
        super(position, size);
        enableScreenBounds(true);
    }

    public Player(int xPos, int yPos, int width, int height) {
        // Activate Parent Constructor //
        super(new Point2D.Double((double) xPos, (double) yPos), (Dimension2D) new Dimension(width, height));
        enableScreenBounds(true);
    }
    // Base Methods //
    @Override
    public void update() {
        // Data //
        Point2D velocity = this.getVelocity();
        // Ancestor Update //
        super.update();
        // Physics //
        updatePhysics();
        // Movemement //
        velocity.setLocation(movementAxis.getX(), velocity.getY());

        for (Entity entity : entityList)
        {
            // Conditions //
            if (this == entity)
                continue;
            if (!entity.hitBox.intersectsLine(new Line2D.Double(getPosition().getX(), getPosition().getY(), getPosition().getX() + 100, getPosition().getY())))
                continue;    
            // Settings //
            System.out.println("INTERSECTING!");
        }
    }
    //

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

    // tracks and dispalys player level
    public static void rank() {
        String[] rank = {"novice", "intermediate", "experienced", "expert"};
    }

    // Input //
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
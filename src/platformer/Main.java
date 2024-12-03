/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

package platformer;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.*;
import java.awt.event.KeyListener;
import java.util.Dictionary;
import java.util.Hashtable;
import java.awt.geom.Point2D;

import platformer.Entities.Platform;
import platformer.Entities.Player;
import platformer.Gui.CoreFrame;
/**
 *
 * @author: Nicholas Ranin
 * @date: Nov 27, 2024
 * filename: Platformer.java
 * @description: Fill In!
 */

public class Main
{
    // Graphics //
    public static final GraphicsEnvironment GE = GraphicsEnvironment.getLocalGraphicsEnvironment();
    public static final GraphicsDevice[] displays = GE.getScreenDevices();
    public static final Dictionary<GraphicsDevice, CoreFrame> CoreFrames = new Hashtable<>();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        new Platform(500, 500, 500, 100);
        // TODO code application logic here

        for (GraphicsDevice display : displays) 
        {
            CoreFrame coreFrame = new CoreFrame(display);
            CoreFrames.put(display, coreFrame);
            System.out.println(display);
        }

        Player mainPlayer = new Player(new Point2D.Double(100, 100));
        mainPlayer.setGravityEffect(true);
    }
}

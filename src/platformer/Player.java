package platformer;

import platformer.Gui.*;
import platformer.Entities.*;
import java.awt.GraphicsDevice;
import java.awt.geom.Point2D;
import java.awt.geom.Dimension2D;

public class Player {
    // Data //
    CoreFrame coreFrame;
    DrawCanvas drawCanvas;
    PlayerCharacter character;
    Camera camera;
    GraphicsDevice viewDisplay;
    int playerID; 

    // Constructor //
    /** Creates a player
     * @param playerID identifying number of the player
     * @param screenDisplay the display device of this players view
     * @param screenLocation the location this players view
     * @param screenSize the size of this players view
     */
    public Player(int playerID, GraphicsDevice viewDisplay, Point2D viewLocation, Dimension2D viewSize){
        this.playerID = playerID;
        this.viewDisplay = viewDisplay;

        
    }
}
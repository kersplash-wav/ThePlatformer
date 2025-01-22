package platformer;

import platformer.Gui.*;
import platformer.Services.InputService;
import platformer.Entities.*;
import platformer.Entities.Character;
import java.awt.geom.Point2D;
import java.awt.Graphics;
import static java.awt.event.KeyEvent.*;
import java.awt.Point;

import java.awt.Color;

public class Player {
    // Instance Data //
    /**
     * The graphics object which is used with paintComponent on the camera
     */
    Graphics graphics;
    /**
     * The character entity object
     */
    Character character;
    /**
     * The parent frame of the camera
     */
    CoreFrame coreFrame;
    /**
     * The camera, a child of the parent frame / core frame
     */
    Camera camera;
    /**
     * The InputService for checking whether a key is down
     */
    InputService input = InputService.GetInputService();

    /**
     * Constructs a player, which constructs its character too.
     */
    public Player() {
        // Initialize Character //
        character = new Character();
        character.setGravityEffect(true);
        coreFrame = Main.coreFrame;
        camera = Main.camera;
        graphics = Main.graphics;
    }

    /**
     * Renders all the entities
     */
    public void render() {
        graphics = camera.getGraphics();
        Point2D offset = camera.getPosition();
        for (Entity entity : Entity.entityList) {
            entity.render(graphics, offset);
        }
    }

    /**
     * Getter for the character position
     * @return
     * The character position
     */
    public Point2D getPosition() {
        return character.getPosition();
    }

    /**
     * Periodic function, handles movement
     */
    public void periodic() {
        double x = 0;
        double y = 0;
        // Checks //
        if (input.IsKeyDown(VK_W))
            if (character.isGrounded()||(character.isLeftWalled()&&character.getLeftWall().getFriction()<-0.1)||(character.isRightWalled()&&character.getRightWall().getFriction()<-0.1))
                y -= 5;
        if (input.IsKeyDown(VK_S))
            if (!character.isGrounded())
                y += 1;
        if (input.IsKeyDown(VK_A))
            if (!character.isLeftWalled())
                x -= 0.1;
        if (input.IsKeyDown(VK_D))
            if (!character.isRightWalled())
                x += 0.1;

        character.setMovementAxis(new Point2D.Double(x, y));
    }
}
package platformer;

import platformer.Gui.*;
import platformer.Services.InputService;
import platformer.Entities.*;
import platformer.Tools.*;
import java.awt.geom.Point2D;
import java.awt.Graphics;
import static java.awt.event.KeyEvent.*;
import java.awt.Point;

import java.awt.Color;

public class Player {
    // Instance Data //
    Graphics graphics;
    PlayerCharacter character;
    CoreFrame coreFrame;
    Camera camera;
    Tool equipedTool;
    InputService input = InputService.GetInputService();

    /**
     * Creates a player
     */
    public Player() {
        // Initialize Character //
        character = new PlayerCharacter();
        character.setGravityEffect(true);
        character.setColour(Color.GRAY);
        coreFrame = Main.coreFrame;
        camera = Main.camera;
        graphics = Main.graphics;
    }

    public void render() {
        graphics = camera.getGraphics();
        Point2D offset = camera.getPosition();
        for (Entity entity : Entity.entityList) {
            entity.render(graphics, offset);
        }
    }

    public Point2D getPosition() {
        return character.getPosition();
    }

    public void periodic() {
        double x = 0;
        double y = 0;
        // Checks //
        if (input.IsKeyDown(VK_W))
            y += 0.1;
        if (input.IsKeyDown(VK_S))
            y -= 0.1;
        if (input.IsKeyDown(VK_A))
            x -= 0.1;
        if (input.IsKeyDown(VK_D))
            x += 0.1;

        character.setMovementAxis(new Point2D.Double(x, y));
    }
}
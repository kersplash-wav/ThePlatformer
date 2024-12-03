package platformer.Entities;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;

public class Camera 
{
    // Character //
    public Entity character;
    // Coordinates //
    public Point2D position;
    // Constructor //
    public Camera() {}
    // Base Methods //
    public void render(Graphics2D graphics)
    {
        // Render Character //
        character.render(graphics);
        // Render Everything Else //
        for (var entity : Entity.entityList)
        {
            // Conditions //
            if (entity == character)
                continue;
            // Success //
            entity.render(graphics, position);
        }
    }
}
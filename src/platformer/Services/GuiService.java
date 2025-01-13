// Directory //
package platformer.Services;

import platformer.Main;
import platformer.Entities.Entity;
import platformer.Entities.EntityConstants;
import platformer.Entities.Platform;
import platformer.Gui.CoreFrame;
import java.awt.*;
import java.io.File;

import javax.imageio.ImageIO;

public class GuiService {

    public static void paintComponent(Graphics _graphics) {
        Graphics2D graphics = (Graphics2D) _graphics;
        // Draw Background //
        try {
            Dimension screenSize = Main.coreFrame.getSize();
            Image bgImage = ImageIO.read(new File("src/platformer/Gui/Images/CSlevel001.png"));
            // drawTitleScreen(graphics);
            graphics.drawImage(bgImage, 0, 0, screenSize.width, screenSize.height, null);
        }

        catch (Exception e) {
        }

        drawLevelOne(graphics);
    }

    public static void drawTitleScreen(Graphics2D graphics) {
        Dimension screenSize = Main.coreFrame.getSize();
        double xScreen = screenSize.getWidth();
        double yScreen = screenSize.getHeight();
        Color titleTextShadow = new Color(47, 84, 84); // darker colour will be put behind the lighter title screen text
                                                       // to improve visual design
        Color titleTextColor = new Color(192, 255, 254); // title screen text used to display the game's name
        Font titleFont = new Font("Serif", Font.BOLD, 28); // creating the font used
        // SETTING FONT FOR ALL TEXT
        graphics.setFont(titleFont);

        graphics.setColor(titleTextShadow);
        graphics.drawString("Test", Math.round(xScreen / 2), Math.round(yScreen / 2)); // (hopefully) creates the shadow
                                                                                       // for the title text

        graphics.setColor(titleTextColor);
        graphics.drawString("Test", Math.round(xScreen / 2.1), Math.round(yScreen / 2.1)); // (hopefully) creates the
                                                                                           // main text - it should be
        // above the shadow
    }

    public static void drawLevelOne(Graphics graphics) {
        Main.setFrame(-1000, 3000, -1000, 500);
        Image levelOneBackground = Toolkit.getDefaultToolkit().getImage("platformer/Gui/Images/CSlevel001.png");
        Platform platform1 = new Platform(-150, 500, 500, 12); // the player needs to spawn at x = 850
        Platform platform2 = new Platform(350, 470, 500, 12);
        Platform platform3 = new Platform(750, 500, 500, 12);
        Platform platform4 = new Platform(1050, 470, 100, 12);
        Platform platform5 = new Platform(1250, 500, 100, 12);
        Platform platform6 = new Platform(1400, 470, 100, 12);
        Platform platform7 = new Platform(1550, 500, 200, 12);
        Platform platform8 = new Platform(2145, 500, 200, 12); // trampoline platform?
        platform1.applyPreset(EntityConstants.PlatformConstants.RubberPlatform);
        // platform8.setColour(Color.GREEN);
        // platform8.setBounce(-5);
        Platform platform9 = new Platform(3400, 1500, 400, 12);
        Platform platform10 = new Platform(3900, 1600, 200, 12); // end platform
        //platform10.setColour(Color.GREEN);
    }

}
// Directory //
package platformer.Services;

import platformer.Main;
import platformer.Gui.CoreFrame;
import java.awt.*;
import java.io.File;

import javax.imageio.ImageIO;

class GuiService {

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

    public static void drawLevelOne(Graphics2D graphics) {
        Image levelOneBackground = Toolkit.getDefaultToolkit().getImage("E:\CSlevel001.png");
        Platform platform1 = new Platform(850, 1500, 400, 100); // the player needs to spawn at x = 850
        Platform platform2 = new Platform(1350, 1470, 300, 100);
        Platform platform3 = new Platform(1750, 1500, 200, 100);
        Platform platform4 = new Platform(2050, 1470, 100, 100);
        Platform platform5 = new Platform(2250, 1500, 50, 100);
        Platform platform6 = new Platform(2400, 1470, 50, 100);
        Platform platform7 = new Platform(2550, 1500, 400, 100);
        Platform platform8 = new Platform(3145, 1500, 60, 100); // trampoline platform?
        platform8.setColour(Color.BLACK);
        platform8.setBounce(Constants.Platform.RubberPlatform.bounce);
        Platform platform9 = new Platform(3400, 1500, 400, 100);
        Platform platform10 = new Platform(3900, 1600, 200, 100); // end platform
        platform10.setColour(Color.GREEN);
    }

}
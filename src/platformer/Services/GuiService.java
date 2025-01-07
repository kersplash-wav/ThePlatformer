// Directory //
package platformer.Services;

import platformer.Main;
import platformer.Gui.CoreFrame;
import java.awt.*;

class GuiService {

    public static void paintComponent(Graphics _graphics) {
        Graphics2D graphics = (Graphics2D) _graphics;
        drawTitleScreen(graphics);
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

        graphics.drawRect((Math.round(xScreen / 2)), (Math.round(yScreen / 2.1)), (Math.round(xScreen - xScreen * 0.9)),
                (Math.round(yScreen * 0.07))); // line underneath the
        // large title text to
        // make visuals better
    }
}
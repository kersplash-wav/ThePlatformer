// Directory //
package platformer.Services;

import java.awt.*;
import javax.swing.JPanel;
import javax.swing.JFrame;

class GuiService {

    public static void paintComponent(Graphics _graphics) {
        Graphics2D graphics = (Graphics2D) _graphics;
        drawTitleScreen(graphics);
    }

    public static void drawTitleScreen(Graphics2D graphics) {
        Color titleTextShadow = new Color(47, 84, 84); // darker colour will be put behind the lighter title screen text
                                                       // to improve visual design
        Color titleTextColor = new Color(192, 255, 254); // title screen text used to display the game's name
        Font titleFont = new Font("Serif", Font.BOLD, 28); // creating the font used

        graphics.setFont(titleFont);
        graphics.setColor(titleTextShadow);
        graphics.drawString("Test", 50, 50); // (hopefully) creates the shadow for the title text

        graphics.setColor(titleTextColor);
        graphics.drawString("Test", 55, 55); // (hopefully) creates the main text - it should be above the shadow

        graphics.drawRect(0, 70, 50, 50); // line underneath the large title text to make visuals better
    }
}
package midproject.utilities.viewComponents;

import javax.swing.*;
import java.awt.*;

/**
 * Class for creating a texted button with specified dimensions and font
 */
public class Button extends JButton {
    public Button(String buttonText, int width, int height, Font font) {
        super(buttonText); // Set button text

        setFocusable(false); // Disable focusability
        setPreferredSize(new Dimension(width, height)); // Set preferred size
        setFont(font); // Set font
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Set cursor to hand when hovering
    } // end of Button Constructor

    //TODO: other methods such as handling user clicks
} // end of Button class

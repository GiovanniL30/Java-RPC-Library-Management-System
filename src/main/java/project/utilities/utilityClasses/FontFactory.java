package project.utilities.utilityClasses;

import java.awt.*;
import java.io.Serializable;

public class FontFactory implements Serializable {

    /**
     * Creates a new Font object with the "Poppins" font family, plain style, and the specified font size.
     * @param  fontSize the font size
     * @return a Font object with the specified properties
     */
    public static Font newPoppinsDefault(int fontSize) {
        return new Font("Poppins", Font.PLAIN, fontSize);
    }

    /**
     * Creates a new Poppins bold font with the specified font size.
     * @param  fontSize the size of the font
     * @return the new Poppins bold font
     */
    public static Font newPoppinsBold(int fontSize) {
        return new Font("Poppins", Font.BOLD, fontSize);
    }
} // end of FontFactory class


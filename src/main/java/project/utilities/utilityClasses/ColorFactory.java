package project.utilities.utilityClasses;

import java.awt.*;
import java.io.Serializable;

/**
 * A factory class for creating Color objects with predefined colors.
 */
public class ColorFactory implements Serializable {

    /**
     * Returns a Color object representing red.
     *
     * @return The Color object representing red.
     */
    public static Color red(){
        return new Color(120, 20, 20);
    }

    /**
     * Returns a Color object representing blue.
     *
     * @return The Color object representing blue.
     */
    public static Color blue(){
        return new Color(68, 137, 240);
    }

    /**
     * Returns a Color object representing green.
     *
     * @return The Color object representing green.
     */
    public static Color green(){
        return new Color(43, 152, 43);
    }

    /**
     * Returns a Color object representing black.
     *
     * @return The Color object representing black.
     */
    public static Color black() { return new Color(2, 2, 2); }

    /**
     * Returns a Color object representing purple.
     *
     * @return The Color object representing purple.
     */
    public static Color purple() { return new Color(120, 37, 198); }

    /**
     * Returns a Color object representing white.
     *
     * @return The Color object representing white.
     */
    public static Color white() { return new Color(255, 255, 255); }

}

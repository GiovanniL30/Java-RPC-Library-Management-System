package project.utilities.utilityClasses;

import java.awt.*;
import java.io.Serializable;

public class ColorFactory implements Serializable {

    public static Color red(){
        return new Color(120, 20, 20);
    }

    public static Color blue(){
        return new Color(68, 137, 240);
    }

    public static Color green(){
        return new Color(43, 152, 43);
    }

    public static Color black() { return new Color(2, 2, 2); }

    public static Color purple() { return new Color(120, 37, 198); }

    public static Color white() { return new Color(255, 255, 255); }

}

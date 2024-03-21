package project.server.views.components;

import project.utilities.utilityClasses.FontFactory;
import project.utilities.viewComponents.FieldInput;
import project.utilities.viewComponents.IconButton;

import javax.swing.*;
import java.awt.*;

public class PanelHeader extends JPanel {

    private ClickableText button1 = new ClickableText("", 100, 50, FontFactory.newPoppinsBold(16));
    private ClickableText button2 = new ClickableText("", 100, 50, FontFactory.newPoppinsBold(16));
    private ClickableText button3 = new ClickableText("", 100, 50, FontFactory.newPoppinsBold(16));
    private FieldInput searchInput = new FieldInput("", new Dimension(200, 50), 20, 1, false);
    private IconButton cancelSearchButton = new IconButton("", 50, 50);
    private IconButton searchButton = new IconButton("", 50, 50);

    public PanelHeader(String button1, String button2, String button3) {
        this.button1.setText(button1);
        this.button1.setText(button2);
        this.button1.setText(button3);

    }

}

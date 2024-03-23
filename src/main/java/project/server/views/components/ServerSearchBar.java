package project.server.views.components;

import project.utilities.utilityClasses.FontFactory;
import project.utilities.viewComponents.Button;


import javax.swing.*;
import java.awt.*;

public class ServerSearchBar extends JPanel {
    private final Button cancelSearch;
    private final JTextField inputField;
    public ServerSearchBar(Dimension dimension) {
        setPreferredSize(dimension);
        setLayout(new GridBagLayout());
        setBackground(Color.white);

        GridBagConstraints layout = new GridBagConstraints();
        layout.anchor = GridBagConstraints.WEST;
        layout.gridx = 0;
        layout.gridy = 0;
        layout.weightx = 1.0;
        layout.fill = GridBagConstraints.HORIZONTAL;
        layout.insets = new Insets(0, 5, 0, 5);
        inputField = new JTextField();
        inputField.setPreferredSize(new Dimension(200, 40));
        layout.gridwidth = 2;
        add(inputField, layout);

        cancelSearch = new Button("X", 50, 50, FontFactory.newPoppinsDefault(13));
        layout.gridx = 2;
        layout.weightx = 0;
        add(cancelSearch, layout);
    }
//    public String getSearch() {
//        return inputField;
//    }
//
    public Button getCancelButton() {
        return cancelSearch;
    }
//    public FieldInput getInput() {
//        return inputField;
//    }
}

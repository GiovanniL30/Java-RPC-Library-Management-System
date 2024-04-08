package midproject.server.views.components;

import midproject.utilities.utilityClasses.ColorFactory;
import midproject.utilities.utilityClasses.FontFactory;
import midproject.utilities.viewComponents.Button;


import javax.swing.*;
import java.awt.*;

public class ServerSearchBar extends JPanel {
    private final Button cancelSearch =  new Button("X", 50, 50, FontFactory.newPoppinsDefault(13));
    private final JTextField inputField = new JTextField();
    private final Button searchButton = new Button("Search", 80, 50, FontFactory.newPoppinsDefault(13));
    public ServerSearchBar(Dimension dimension) {
        setPreferredSize(dimension);
        setLayout(new FlowLayout(FlowLayout.RIGHT));
        setBackground(Color.WHITE);

        inputField.setPreferredSize(new Dimension(400, 50));
        searchButton.setForeground(Color.WHITE);
        searchButton.setBackground(ColorFactory.blue());

        add(inputField);
        add(cancelSearch);
        add(searchButton);
    }
    public Button getCancelSearch() {
        return cancelSearch;
    }

    public String getSearch() {
        return inputField.getText().toLowerCase().trim();
    }

    public JTextField getInputField() {
        return inputField;
    }

    public Button getSearchButton() {
        return searchButton;
    }
}

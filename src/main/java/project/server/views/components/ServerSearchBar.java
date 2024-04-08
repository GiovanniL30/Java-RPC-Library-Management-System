package project.server.views.components;

import project.utilities.utilityClasses.ColorFactory;
import project.utilities.utilityClasses.FontFactory;
import project.utilities.viewComponents.Button;


import javax.swing.*;
import java.awt.*;

/**
 * Represents a search bar component for the server GUI.
 * It consists of an input field, a cancel search button, and a search button.
 */

public class ServerSearchBar extends JPanel {
    private final Button cancelSearch =  new Button("X", 50, 50, FontFactory.newPoppinsDefault(13));
    private final JTextField inputField = new JTextField();
    private final Button searchButton = new Button("Search", 80, 50, FontFactory.newPoppinsDefault(13));

    /**
     * Constructs the ServerSearchBar.
     * @param dimension The preferred size of the search bar.
     */
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
    } // end of constructor

    // ---- GETTERS ----
    /**
     * Retrieves the cancel search button.
     */
    public Button getCancelSearch() {
        return cancelSearch;
    }

    /**
     * Retrieves the text entered in the search input field.
     */
    public String getSearch() {
        return inputField.getText().toLowerCase().trim();
    }

    /**
     * Retrieves the search input field.
     */
    public JTextField getInputField() {
        return inputField;
    }

    /**
     * Retrieves the search button.
     */
    public Button getSearchButton() {
        return searchButton;
    }
} // end of ServerSearchBar class

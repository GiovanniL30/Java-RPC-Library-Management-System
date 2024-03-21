package project.server.views.components;

import project.utilities.utilityClasses.ColorFactory;
import project.utilities.utilityClasses.FontFactory;
import project.utilities.viewComponents.Button;
import project.utilities.viewComponents.FieldInput;

import javax.swing.*;
import java.awt.*;

/**
 * A class for creating a search bar
 */

public class ServerSearchBar extends JPanel {

    private final FieldInput input;
    private final Button cancelSearch;

    /**
     * Constructs a new SearchBar with the specified dimension and background color.
     *
     * @param dimension            The preferred size of the search bar.
     * @param isDarkBlueBackground Determines whether the search bar has a dark blue background or not.
     */
    public ServerSearchBar(Dimension dimension, boolean isDarkBlueBackground) {
        setPreferredSize(dimension); // Set the preferred size
        setLayout(new GridBagLayout()); // Set layout
        setBackground(Color.white); // Set background color

        GridBagConstraints layout = new GridBagConstraints();
        layout.anchor = GridBagConstraints.WEST;


        // Create and configure the input field
        layout.gridy = 0;
        layout.gridx = 1;
        layout.weightx = 2.0;
        layout.insets = new Insets(0, -70, 0, 0);

        // Create and configure the input field for search query
        if (isDarkBlueBackground) {
            input = new FieldInput("", new Dimension((int) (dimension.getWidth()), (int) (dimension.getHeight() - 50)), 30, 0, false);
            input.setBackground(ColorFactory.blue());
        } else {
            input = new FieldInput("", new Dimension((int) (dimension.getWidth()), (int) (dimension.getHeight() - 50)), 30, 0, false);
            input.setBackground(Color.white);
        }
        add(input, layout); // Add the input field to the search bar

        // Create and configure the cancel search button
        cancelSearch = new project.utilities.viewComponents.Button("X", 50, 50, FontFactory.newPoppinsDefault(13));
        layout.gridx = 2;
        layout.weightx = 0;
        add(cancelSearch, layout); // Add the cancel search button to the search bar

    } // end of SearchBar constructor

    /**
     * A method to retrieve the search input.
     *
     * @return the search input
     */
    public String getSearch() {
        return input.getInput();
    }

    /**
     * Retrieves the cancel button for the search functionality.
     *
     * @return the cancel button
     */
    public Button getCancelButton() {
        return cancelSearch;
    }

    /**
     * Get the input field.
     *
     * @return the input field
     */
    public FieldInput getInput() {
        return input;
    }
} // end of SearchBar class

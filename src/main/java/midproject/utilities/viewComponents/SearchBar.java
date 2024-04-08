package midproject.utilities.viewComponents;

import midproject.utilities.utilityClasses.ColorFactory;
import midproject.utilities.utilityClasses.FontFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;

/**
 * A class for creating a search bar
 */

public class SearchBar extends JPanel {

    private final FieldInput input;
    private final DropDown dropDown;
    private final DropDown filterType;
    private final Button cancelSearch;

    /**
     * Constructs a new SearchBar with the specified dimension and background color.
     *
     * @param dimension            The preferred size of the search bar.
     * @param isDarkBlueBackground Determines whether the search bar has a dark blue background or not.
     */
    public SearchBar(Dimension dimension, boolean isDarkBlueBackground) {
        setPreferredSize(dimension); // Set the preferred size
        setLayout(new GridBagLayout()); // Set layout
        setBackground(Color.white); // Set background color

        GridBagConstraints layout = new GridBagConstraints();
        layout.anchor = GridBagConstraints.WEST;

        // Create and configure the dropdown for selecting search criteria
        layout.gridx = 0;
        layout.weightx = 1.0;
        dropDown = new DropDown(new Dimension(70, 48), false, "Title", "Genre", "Author");
        filterType = new DropDown(new Dimension(500, 48), true, "Comedy", "Horror", "Fantasy", "Fiction", "Novel", "Sci-Fi",
                "Young", "Adult", "Historical", "Thriller", "Fantasy", "Science", "Romance", "Mystery");
        add(dropDown, layout);

        // Create and configure the input field
        layout.gridy = 0;
        layout.gridx = 1;
        layout.weightx = 2.0;
        layout.insets = new Insets(0, -70, 0, 0);

        // Create and configure the input field for search query
        if (isDarkBlueBackground) {
            input = new FieldInput("", new Dimension((int) (dimension.getWidth() / 1.5), (int) (dimension.getHeight() - 50)), 30, 0, false);
            input.setBackground(ColorFactory.blue());
        } else {
            input = new FieldInput("", new Dimension((int) (dimension.getWidth() / 1.5), (int) (dimension.getHeight() - 50)), 30, 0, false);
            input.setBackground(Color.white);
        }
        add(input, layout); // Add the input field to the search bar

        // Create and configure the cancel search button
        cancelSearch = new Button("X", 50, 50, FontFactory.newPoppinsDefault(13));
        layout.gridx = 2;
        layout.weightx = 0;
        add(cancelSearch, layout); // Add the cancel search button to the search bar

        // Add a listener for the dropdown selection
        dropDown.addItemListener((e) -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                layout.gridy = 0;
                layout.gridx = 1;
                layout.weightx = 2.0;

                // Show/Hide the input field based on the selected dropdown
                if (dropDown.getSelectedItem().equals("Genre")) {
                    remove(input);
                    add(filterType, layout);
                    cancelSearch.setVisible(false);
                } else {
                    remove(filterType);
                    add(input, layout);
                    cancelSearch.setVisible(true);
                }
                revalidate(); // revalidate the panel to ensure proper layout
                repaint(); // Repaint the panel to reflect the changes
            }
        }); // end of addItemListener
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
     * Get the selected filter type from the dropdown
     *
     * @return the selected filter type
     */
    public String getFilterType() {
        return dropDown.dropDownChoice();
    }

    /**
     * Retrieves the genre drop-down.
     *
     * @return the genre drop-down
     */
    public DropDown getGenreDropDown() {
        return filterType;
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

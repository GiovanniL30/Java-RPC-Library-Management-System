package project.utilities.viewComponents;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

/**
 * A class designed for drop-down menus
 */

public class DropDown extends JComboBox<String> {
    /**
     * Constructs a DropDown object with specified parameters.
     * @param dimension the preferred dimension of the drop-down menu
     * @param sorted true if the choices should be sorted, false otherwise
     * @param choices the options to be displayed in the drop-down menu
     */
    public DropDown(Dimension dimension, boolean sorted, String... choices) {
        // sort the choices if specified
        if(sorted) Arrays.sort(Arrays.stream(choices).toArray());
        // set the model for the drop-down menu
        setModel(new DefaultComboBoxModel<>(choices));

        // set initial properties of the drop-down menu
        setSelectedIndex(0);
        setToolTipText("Filter");
        setPreferredSize(new Dimension(dimension));
    } // end of DropDown constructor

    /**
     * A method to retrieve the selected item from a drop-down menu.
     * @return the selected item as a string
     */
    public String dropDownChoice(){
        return getSelectedItem().toString();
    } // end of dropDownChoice method
} // end of DropDown class



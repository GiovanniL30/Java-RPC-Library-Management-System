package project.utilities.utilityClasses;

import javax.swing.*;

/**
 * A utility class for getting an IP address from the user using a dialog box.
 */

public class IPGetter {
    /**
     * Prompts the user to enter an IP address using a dialog window.
     *
     * @param message the message to display when asking for IP address
     * @return the IP address entered by the user
     */
    public static String askUserForIP(String message) {
        String ipAddress;

        // Repeat the input prompt until a non-empty IP address is entered
        do {
            // Display the input prompt
            ipAddress = JOptionPane.showInputDialog(null, message);

            // exit the program if the user cancels
            if (ipAddress == null) {
                System.exit(0);
            }

            // Show a message if the entered IP address is empty
            if (ipAddress.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please Enter something...");
            }
        } while (ipAddress.isEmpty()); // Loop until a non-empty IP address is entered

        return ipAddress; // Return the entered IP address
    } // End of askUserForIP method
} // End of IPGetter class


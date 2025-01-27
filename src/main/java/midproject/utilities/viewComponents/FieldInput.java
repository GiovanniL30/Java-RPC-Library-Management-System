package midproject.utilities.viewComponents;

import javax.swing.*;
import java.awt.*;

/**
 *  A custom JPanel class for user input fields, supporting both text and password fields
 */

public class FieldInput extends JPanel {

    private final JTextField textField = new JTextField();
    private final JPasswordField passwordField = new JPasswordField();
    private final JLabel errorMessage;
    private final int maxInput;
    private final int minInput;
    private final boolean isPasswordField;

    /**
     * Constructs an object of type FieldInput with the specified field title, dimension, input constraints, and field type
     * @param fieldTitle the title of the input field
     * @param dimension the preferred dimension of the input field
     * @param maxInput the maximum number of characters allowed
     * @param minInput the minimum required input length
     * @param isPasswordField true if the field is a password field, false otherwise
     */
    public FieldInput(String fieldTitle, Dimension dimension, int maxInput, int minInput, boolean isPasswordField) {
        JLabel fieldLabel = new JLabel(fieldTitle); // Label for the input field
        this.errorMessage = new JLabel(); // Error message
        this.maxInput = maxInput; // Maximum number of characters allowed
        this.minInput = minInput; // Minimum required input length
        this.isPasswordField = isPasswordField; // Indicated whether the input field is a password field
        setBackground(Color.white);

        // Set the preferred size of the panel
        setPreferredSize(new Dimension(dimension.width + 50, dimension.height + 50));
        setLayout(new GridBagLayout()); // Set layout to GridBagLayout

        GridBagConstraints constraints = new GridBagConstraints(); // Constraints for the components
        constraints.gridy = 2;
        constraints.gridx = 1;
        constraints.anchor = GridBagConstraints.WEST;

        // Add the appropriate input field based on the field type
        if (isPasswordField) {
            passwordField.setPreferredSize(dimension);
            //passwordField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK), new EmptyBorder(new Insets(10, 10, 10, 10))));
            add(passwordField, constraints);
        } else {
            textField.setPreferredSize(dimension);
            //textField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK), new EmptyBorder(new Insets(10, 10, 10, 10))));
            add(textField, constraints);
        } // end of if-else

        // Add the field label
        constraints.gridy = 1;
        constraints.gridx = 1;
        add(fieldLabel, constraints);

        // Add the error message
        constraints.gridy = 3;
        errorMessage.setForeground(Color.RED);
        errorMessage.setVisible(false);
        add(errorMessage, constraints);
    }

    /**
     * A method to get the user input.
     * @return the user input as a String, or null if the input is not valid
     */
    public String getInput() {
        if (validateUserInput()) {
            removeError();
            if (isPasswordField) {
                return new String(passwordField.getPassword()).trim().replaceAll("\\s+", " ");
            } else {
                return textField.getText().trim().replaceAll("\\s+", " ");
            }
        }
        return null;
    } // end of getInput method

    /**
     * Validate user input and return true if the input is within the specified length range.
     * @return true if the input is within the length range, false otherwise
     */
    private boolean validateUserInput() {
        String userInput;
        // Get user input
        if (isPasswordField) {
            userInput = new String(passwordField.getPassword());
        } else {
            userInput = textField.getText();
        }

        // Validate user input
        if (userInput.trim().replace(" ", "").length() < minInput || userInput.length() > maxInput) {
            enableError("Please enter text with a length of [" + minInput + "-" + maxInput + "]");
            return false;
        }
        return true;
    } // end of validateUserInput method

    /**
     * Enables error message and sets the provided message.
     * @param  message the error message to be displayed
     */
    public void enableError(String message) {
//        if (isPasswordField) {
//            passwordField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.RED), new EmptyBorder(new Insets(10, 10, 10, 10))));
//        } else {
//            textField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.RED), new EmptyBorder(new Insets(10, 10, 10, 10))));
//        }
        errorMessage.setText(message);
        errorMessage.setVisible(true);
    } // end of enableError method

    /**
     * Removes error display and restores the input field's appearance.
     */
    public void removeError() {
//        if (isPasswordField) {
//            passwordField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK), new EmptyBorder(new Insets(10, 10, 10, 10))));
//        } else {
//            textField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK), new EmptyBorder(new Insets(10, 10, 10, 10))));
//        }
        errorMessage.setVisible(false);
    } // end of removeError method
    public void clearText() {
        textField.setText("");
    }
    public void clearPassword() {
        passwordField.setText("");
    }

    /**
     * Retrieves the text field.
     * @return the text field
     */
    public JTextField getTextField() {
        return textField;
    }

    /**
     * Returns the password field.
     * @return the password field
     */
    public JPasswordField getPasswordField() {
        return passwordField;
    } // end of getPasswordField method
} // end of FieldInput class


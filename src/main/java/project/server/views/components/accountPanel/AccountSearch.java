package project.server.views.components.accountPanel;

import project.utilities.utilityClasses.ColorFactory;
import project.utilities.utilityClasses.FontFactory;
import project.utilities.viewComponents.Button;

import javax.swing.*;
import java.awt.*;

/**
 * Represents a panel for searching accounts
 */

public class AccountSearch extends JPanel {
    private final Button cancel;
    private final Button search;
    private final JTextField inputField;
    private final Button createAccount = new Button("Create Account", 160, 50, FontFactory.newPoppinsDefault(14));

    /**
     * Constructs an AccountSearch panl with the specified dimension
     */
    public AccountSearch(Dimension dimension) {
        setPreferredSize(dimension);
        setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        setBackground(Color.white);

        add(createAccount);

        inputField = new JTextField();
        inputField.setPreferredSize(new Dimension(600, 50));
        add(inputField);

        cancel = new Button("X", 50, 50, FontFactory.newPoppinsDefault(13));
        add(cancel);

        search = new Button("Search", 80, 50, FontFactory.newPoppinsDefault(13));
        search.setBackground(ColorFactory.blue());
        search.setForeground(Color.WHITE);
        add(search);
    }

    /**
     * Gets the cancel button
     */
    public Button getCancel() {
        return cancel;
    }

    /**
     * Gets the search button
     */
    public Button getSearch() {
        return search;
    }

    /**
     * Gets the input text field
     */
    public JTextField getInputField() {
        return inputField;
    }

    /**
     * Gets the create account button
     */
    public Button getCreateAccount() {
        return createAccount;
    }

    /**
     * Displays an error message in the dialog box
     */
    public void enableError(String message) {
        JOptionPane.showMessageDialog(null, message, "Search Error", JOptionPane.INFORMATION_MESSAGE);
    }
} // end of AccountSearch class

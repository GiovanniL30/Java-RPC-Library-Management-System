package midproject.client.views.components;

import midproject.utilities.utilityClasses.ColorFactory;
import midproject.utilities.utilityClasses.FontFactory;
import midproject.utilities.viewComponents.Button;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

/**
 * Class used for the meny of the client
 */
public class Menu extends JPanel {

    private final LinkedList<Button> buttons = new LinkedList<>();
    private final Button homeButton;
    private final Button pendingBooks;
    private final Button borrowedBooks;
    private final Button account;
    private Button currentButton;

    /**
     * Constructor
     */
    public Menu() {
        setBackground(ColorFactory.blue());

        // instantiate buttons
        homeButton = new Button("Books", 200, 50, FontFactory.newPoppinsBold(15));
        pendingBooks = new Button("Pending Books", 200, 50, FontFactory.newPoppinsBold(15));
        borrowedBooks = new Button("Borrowed Books", 200, 50, FontFactory.newPoppinsBold(15));
        account = new Button("Account", 200, 50, FontFactory.newPoppinsBold(15));

        buttons.add(homeButton);
        buttons.add(pendingBooks);
        buttons.add(borrowedBooks);
        buttons.add(account);

        // set layout to GridBagLayout
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(0, 20, 0, 20);
        setLayout(new GridBagLayout());

        constraints.gridy = 0;
        constraints.gridx = 0;

        // add the buttons using the specified constraints
        add(homeButton, constraints);

        constraints.gridx = 1;
        add(pendingBooks, constraints);

        constraints.gridx = 2;
        add(borrowedBooks, constraints);

        constraints.gridx = 3;
        add(account, constraints);
    } // end of constructor

    /**
     * Enables/Disables the current button
     * @param currentButton
     */
    public void setCurrentButton(Button currentButton) {

        this.currentButton = currentButton;
        currentButton.setEnabled(false);

        for(Button button: buttons) {

            if(!button.equals(currentButton)){
                // enable buttons that are not pressed
                button.setEnabled(true);
            } // end of if statement

        } // end of for each
    } // end of setCurrentButton

    /**
     * Gets the home button
     * @return
     */
    public Button getHomeButton() {
        return homeButton;
    } // end of getHomeButton

    /**
     * Gets the pending books button
     * @return
     */
    public Button getPendingBooks() {
        return pendingBooks;
    } // end of getPendingBooks

    /**
     * Gets the borrowed books button
     * @return
     */
    public Button getBorrowedBooks() {
        return borrowedBooks;
    } // end of getBorrowedBooks

    /**
     * Gets the current button
     * @return
     */
    public Button getCurrentButton() {
        return currentButton;
    } // end of getCurrentButton

    /**
     * Gets the account
     * @return
     */
    public Button getAccount() {
        return account;
    } // end of getAccount
} // end of class


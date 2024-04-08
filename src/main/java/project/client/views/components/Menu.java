package project.client.views.components;

import project.client.controller.ClientController;
import project.utilities.utilityClasses.ColorFactory;
import project.utilities.utilityClasses.FontFactory;
import project.utilities.viewComponents.Button;

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

       GridBagConstraints constraints = new GridBagConstraints();
       constraints.insets = new Insets(0, 20, 0, 20);
       setLayout(new GridBagLayout());

       constraints.gridy = 0;
       constraints.gridx = 0;

       add(homeButton, constraints);

       constraints.gridx = 1;
       add(pendingBooks, constraints);

       constraints.gridx = 2;
       add(borrowedBooks, constraints);

       constraints.gridx = 3;
       add(account, constraints);
    }

    public void setCurrentButton(Button currentButton) {

        this.currentButton = currentButton;
        currentButton.setEnabled(false);

        for(Button button: buttons) {

            if(!button.equals(currentButton)){
                button.setEnabled(true);
            }

        }
    }

    public Button getHomeButton() {
        return homeButton;
    }

    public Button getPendingBooks() {
        return pendingBooks;
    }

    public Button getBorrowedBooks() {
        return borrowedBooks;
    }

    public Button getCurrentButton() {
        return currentButton;
    }

    public Button getAccount() {
        return account;
    }
}

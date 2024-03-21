package project.server.views.components.accountPanel;

import project.server.controller.ServerController;
import project.utilities.referenceClasses.Account;
import project.utilities.utilityClasses.FontFactory;
import project.utilities.viewComponents.FieldInput;
import project.utilities.viewComponents.Button;

import javax.swing.*;
import java.awt.*;

import static project.utilities.utilityClasses.UtilityMethods.haveNullOrEmpty;
import static project.utilities.utilityClasses.UtilityMethods.generateRandomID;

public class Signup extends JFrame {
    private final FieldInput password;
    private final FieldInput firstName;
    private final FieldInput lastName;
    private final FieldInput userName;
    private final FieldInput email;
    private final Button createAccountButton;
    private final GridBagConstraints layout;

    public Signup(Dimension dimension, ServerController serverController) {
        setLayout(new GridBagLayout());
        getContentPane().setBackground(Color.white);
        layout = new GridBagConstraints();

        firstName = new FieldInput("First Name: ", new Dimension(200, 50), 20, 1, false);
        addComponent(firstName, 0, 0);

        lastName = new FieldInput("Last Name: ", new Dimension(200, 50), 20, 1, false);
        addComponent(lastName, 1, 0);

        userName = new FieldInput("Username: ", new Dimension(200, 50), 100, 8, false);
        addComponent(userName, 2, 0);

        email = new FieldInput("Email: ", new Dimension(200, 50), 20, 1, false);
        addComponent(email, 0, 1);

        password = new FieldInput("Password: ", new Dimension(200, 50), 20, 1, false);
        addComponent(password, 3, 0);

        layout.insets = new Insets(20, 200, 0, 0);

        createAccountButton = new Button("Create Account", 200, 40, FontFactory.newPoppinsBold(13));
        createAccountButton.setBackground(Color.BLUE);
        createAccountButton.setForeground(Color.white);
        layout.insets = new Insets(0, 250, 0, 0);
        addComponent(createAccountButton, 4, 0);

        setSize(dimension);
        setVisible(false);
        setResizable(false);

        createAccountButton.addActionListener((e) -> {

            String enteredFirstName = firstName.getInput();
            String enteredLastName = lastName.getInput();
            String enteredUserName = userName.getInput();
            String enteredPassword = password.getInput();
            String enteredEmail = email.getInput();

            if (haveNullOrEmpty(enteredFirstName, enteredLastName, enteredUserName, enteredPassword, enteredEmail)) {
                return;
            }

            Account account = new Account(generateRandomID(), enteredUserName, enteredFirstName, enteredLastName, enteredEmail, enteredPassword);
            serverController.createAccount(account);
        });
    }

    private void addComponent(JComponent component, int y, int x) {
    }

}

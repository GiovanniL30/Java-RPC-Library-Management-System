package midproject.server.views.components.accountPanel;

import midproject.server.controller.ServerObserver;
import midproject.utilities.referenceClasses.Account;
import midproject.utilities.utilityClasses.ColorFactory;
import midproject.utilities.utilityClasses.FontFactory;
import midproject.utilities.utilityClasses.UtilityMethods;
import midproject.utilities.viewComponents.FieldInput;
import midproject.utilities.viewComponents.Button;
import midproject.utilities.viewComponents.Picture;

import javax.swing.*;
import java.awt.*;

/**
 * Represents a dialog for signing up new users.
 */

public class Signup extends JDialog {
    private final FieldInput password = new FieldInput("Password", new Dimension(450, 50), 40, 8, true );
    private final FieldInput firstName = new FieldInput("First Name", new Dimension(450, 50), 40, 1, false );
    private final FieldInput lastName = new FieldInput("Last Name", new Dimension(450, 50), 40, 1, false );
    private final FieldInput userName = new FieldInput("User Name", new Dimension(450, 50), 40, 8, false );
    private final FieldInput email = new FieldInput("Email", new Dimension(450, 50), 18, 18, false );
    private final Button createAccountButton = new Button("Create Account", 225, 50, FontFactory.newPoppinsDefault(13));
    private final Button cancelCreate = new Button("Cancel", 225, 50, FontFactory.newPoppinsDefault(13));

    private ServerObserver serverObserver;

    /**
     * Constructs a Signup dialog with the specified owner frame, dimension, and server observer.
     * @param owner          The owner frame of the dialog.
     * @param dimension      The dimension of the dialog.
     * @param serverObserver The server observer for handling actions related to user accounts.
     */
    public Signup(Frame owner, Dimension dimension, ServerObserver serverObserver) {
        super(owner, "Create account for student", true);
        setResizable(false);
        setBackground(Color.WHITE);
        this.serverObserver = serverObserver;
        setLayout(new BorderLayout());
        setSize(dimension);

        // Header panel
        JPanel header = new JPanel();
        header.setBackground(Color.WHITE);
        header.setPreferredSize(new Dimension(100, 100));
        header.setLayout(new FlowLayout(FlowLayout.LEFT));

        // Logo
        Picture logo = new Picture("src/main/resources/images/logo/logo_vanni.png", 100, 100);
        logo.setBackground(Color.WHITE);
        header.add(logo);

        // Content Holder panel
        JPanel contentHolder = new JPanel();
        contentHolder.setBackground(Color.white);
        contentHolder.setLayout(new BoxLayout(contentHolder, BoxLayout.Y_AXIS));

        // Title Panel
        JPanel titlePanel = new JPanel();
        JLabel title = new JLabel("Create Account for Student");
        titlePanel.setBackground(Color.WHITE);
        titlePanel.add(title);
        title.setFont(FontFactory.newPoppinsBold(25));

        // Fields
        GridLayout fieldGrid = new GridLayout(3, 2);
        JPanel fieldPanel = new JPanel();
        fieldPanel.setBackground(Color.WHITE);
        fieldPanel.setLayout(fieldGrid);
        fieldPanel.add(firstName);
        fieldPanel.add(lastName);
        fieldPanel.add(userName);
        fieldPanel.add(email);
        fieldPanel.add(password);

        // Buttons
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBackground(Color.WHITE);
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 37));
        buttonsPanel.add(cancelCreate);
        buttonsPanel.add(createAccountButton);
        createAccountButton.setForeground(Color.WHITE);
        createAccountButton.setBackground(ColorFactory.blue());

        fieldPanel.add(buttonsPanel);

        contentHolder.add(titlePanel);
        contentHolder.add(fieldPanel);

        add(header, BorderLayout.NORTH);
        add(contentHolder, BorderLayout.CENTER);

        // Add action listeners
        cancelCreate.addActionListener(e -> this.dispose());
        createAccountButton.addActionListener( e ->  {

            String pass = password.getInput();
            String userN = userName.getInput();
            String lastN = lastName.getInput();
            String firstN = firstName.getInput();
            String emailAdd = email.getInput();

            if(UtilityMethods.haveNullOrEmpty(pass, userN, lastN, firstN, emailAdd)) {
                return;
            }

            if(!UtilityMethods.validateEmail(emailAdd)) {
                email.enableError("Please enter a valid slu email format([7digit]@slu.edu.ph)");
                return;
            }

            if(pass.matches(".*\\s+.*") || userN.matches(".*\\s+.*")) {

                if(pass.matches(".*\\s+.*")) {
                    password.enableError("Spaces are not allowed here");
                }

                if(userN.matches(".*\\s+.*")) {
                    userName.enableError("Spaces are not allowed here");
                }

                return;
            }


            Account account = new Account(UtilityMethods.generateRandomID(), userN, firstN, lastN, emailAdd, pass, false);
            serverObserver.createAccount(account);
        });
    } // end of constructor

    /**
     * Gets the username field input
     */
    public FieldInput getUserName() {
        return userName;
    }
} // end of Signup class

package project.server.views.components.accountPanel;

import com.formdev.flatlaf.FlatLightLaf;
import project.server.controller.ServerController;
import project.server.controller.ServerObserver;
import project.utilities.referenceClasses.Account;
import project.utilities.utilityClasses.ColorFactory;
import project.utilities.utilityClasses.FontFactory;
import project.utilities.utilityClasses.UtilityMethods;
import project.utilities.viewComponents.FieldInput;
import project.utilities.viewComponents.Button;
import project.utilities.viewComponents.Picture;

import javax.swing.*;
import java.awt.*;

import static project.utilities.utilityClasses.UtilityMethods.haveNullOrEmpty;
import static project.utilities.utilityClasses.UtilityMethods.generateRandomID;

public class Signup extends JDialog {
    private final FieldInput password = new FieldInput("Password", new Dimension(450, 50), 40, 8, true );
    private final FieldInput firstName = new FieldInput("First Name", new Dimension(450, 50), 40, 1, false );
    private final FieldInput lastName = new FieldInput("Last Name", new Dimension(450, 50), 40, 1, false );
    private final FieldInput userName = new FieldInput("User Name", new Dimension(450, 50), 40, 8, false );
    private final FieldInput email = new FieldInput("Email", new Dimension(450, 50), 18, 18, false );
    private final Button createAccountButton = new Button("Create Account", 225, 50, FontFactory.newPoppinsDefault(13));
    private final Button cancelCreate = new Button("Cancel", 225, 50, FontFactory.newPoppinsDefault(13));

    private ServerObserver serverObserver;

    public Signup(Frame owner, Dimension dimension, ServerObserver serverObserver) {
        super(owner, "Create account for student", true);
        setResizable(false);
        setBackground(Color.WHITE);
        this.serverObserver = serverObserver;
        setLayout(new BorderLayout());
        setSize(dimension);

        JPanel header = new JPanel();
        header.setBackground(Color.WHITE);
        header.setPreferredSize(new Dimension(100, 100));
        header.setLayout(new FlowLayout(FlowLayout.LEFT));

        Picture logo = new Picture("src/main/resources/images/logo/logo_vanni.png", 100, 100);
        logo.setBackground(Color.WHITE);
        header.add(logo);


        JPanel contentHolder = new JPanel();
        contentHolder.setBackground(Color.white);
        contentHolder.setLayout(new BoxLayout(contentHolder, BoxLayout.Y_AXIS));

        JPanel titlePanel = new JPanel();
        JLabel title = new JLabel("Create Account for Student");
        titlePanel.setBackground(Color.WHITE);
        titlePanel.add(title);
        title.setFont(FontFactory.newPoppinsBold(25));

        GridLayout fieldGrid = new GridLayout(3, 2);
        JPanel fieldPanel = new JPanel();
        fieldPanel.setBackground(Color.WHITE);
        fieldPanel.setLayout(fieldGrid);
        fieldPanel.add(firstName);
        fieldPanel.add(lastName);
        fieldPanel.add(userName);
        fieldPanel.add(email);
        fieldPanel.add(password);


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

            Account account = new Account(UtilityMethods.generateRandomID(), userN, firstN, lastN, emailAdd, pass, false);
            serverObserver.createAccount(account);
        });
    }


    public FieldInput getUserName() {
        return userName;
    }
}
